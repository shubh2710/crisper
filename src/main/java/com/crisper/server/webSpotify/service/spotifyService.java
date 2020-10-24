package com.crisper.server.webSpotify.service;

import com.crisper.server.webSpotify.model.PlayList;
import com.crisper.server.webSpotify.model.PlayerInfo;
import com.crisper.server.webSpotify.model.Track;
import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.*;

@Service
public class spotifyService implements ISpotifyNotifer{

    Logger logger = LoggerFactory.getLogger(spotifyService.class);
    private String refreshToken ="BQCne7lHRLPiGyKNuiv2WYI_cK7mwt79vyOLf0dpHaOIX7krc8JpTspzdPARyOtEWkrHpmYEHD-ArWOMDFj3rapSMI7btAZLAIeHx_dL1bvpMdmhf3pSP_IrDqmwTQ0w0h9E7o-pK2MM0viFpFCDGIct6f1abd80tiHD81bI6qU-IisI7I8w7SCWnKXapr1neOhpVM3Vdi-sIIlbjrzCKOoQDyJsYWS3W1nW_QqCY0JDpHiQDWMCzagcHXCA2cdgUGcG_TH_pGE-fRXWk4y4PPq_IFJiRTZaAUBvWTAky9UnrA";

    private String token="BQCne7lHRLPiGyKNuiv2WYI_cK7mwt79vyOLf0dpHaOIX7krc8JpTspzdPARyOtEWkrHpmYEHD-ArWOMDFj3rapSMI7btAZLAIeHx_dL1bvpMdmhf3pSP_IrDqmwTQ0w0h9E7o-pK2MM0viFpFCDGIct6f1abd80tiHD81bI6qU-IisI7I8w7SCWnKXapr1neOhpVM3Vdi-sIIlbjrzCKOoQDyJsYWS3W1nW_QqCY0JDpHiQDWMCzagcHXCA2cdgUGcG_TH_pGE-fRXWk4y4PPq_IFJiRTZaAUBvWTAky9UnrA";
    JSONParser parser = new JSONParser();
    RestTemplate restTemplate=new RestTemplate();
    String activeDevice="";
    private Track currentPlayingTrack;
    SopitifyScheduler scheduler=new SopitifyScheduler();
    private int volume=50;
    private PlayerInfo playerInfo;

    private int index=0;
    Queue<Track> tracks=new PriorityQueue();

    public void setRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
        getRefreshToken();
    }

    private void getRefreshToken() {

        logger.info("calling refresh token");
        String url =  "https://accounts.spotify.com";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Accept", "application/json");
        headers.set("Authorization", "Basic YTFiMGRlZGE1YzgwNDlhNWI1YzgzYmVhMjllM2RmYjI6N2E3MWJiZGYzOGY2NGE3ZjgzYzhhN2NhNjI1MTI5Yjc=");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        UriComponents uriComponents =
                UriComponentsBuilder.fromHttpUrl(url)
                        .path("/api/token")
                        .queryParam("grant_type", "authorization_code")
                        .queryParam("code", refreshToken)
                        .queryParam("redirect_uri","http://localhost:8080/auth" )
                        .build();
        URI uri = uriComponents.toUri();
        HttpEntity<String> request =
                new HttpEntity<String>(headers);
        ResponseEntity<JSONObject> responseEntity =
                restTemplate.postForEntity( uriComponents.toUri(),request,JSONObject.class);
        JSONObject object=responseEntity.getBody();
        refreshToken=(String)object.get("refresh_token");
        token=(String)object.get("access_token");
        logger.info(object.toJSONString());
        playerInfo();
    }

    public void play(String name) {
        try {
            ArrayList<Track> list=getSearchData(name);
            ArrayList<String> activeDeviceList=getAtiveDeviceID();
            checkActive(activeDeviceList);
            currentPlayingTrack=list.get(0);
            addToPlayList(list.get(0).getId());
            playNext();
        } catch (ParseException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void checkActive(ArrayList<String> activeDeviceList) {
        logger.info("check active device");
        String device=activeDevice;
        String url =  "https://api.spotify.com/v1/me/player";
        HttpHeaders headers = getHeaders();
        JSONObject personJsonObject = new JSONObject();
        personJsonObject.put("device_ids", activeDeviceList);
        personJsonObject.put("play", false);

        callPut(url,personJsonObject);
    }

    public void stop() {
        String url =  "https://api.spotify.com/v1/me/player/pause?device_id"+activeDevice;
       callPut(url);
       scheduler.cancel();
    }

    public void playNextSong() {
        if(tracks.isEmpty())
            playNext();
        else onEnd();
    }
    public void playNext() {
            logger.info("Playing next");
            if (activeDevice.isEmpty()) {
                getAtiveDeviceID();
            }
            String url = "https://api.spotify.com/v1/me/player/next?device_id=" + activeDevice;
            callPost(url);
            scheduleTimer(currentPlayingTrack.getDuration());
    }

    private void scheduleTimer(int duration) {
        scheduler.cancel();
        scheduler.schedule(this,duration);
    }

    private void addToPlayList(String songId){
        logger.info("Add to play list");
        if (activeDevice.isEmpty()) {
            getAtiveDeviceID();
        }
        if(!songId.isEmpty() && !activeDevice.isEmpty()) {
            String url = "https://api.spotify.com/v1/me/player/queue?uri=" + songId + "&device_id=" + activeDevice;
            ArrayList<String> deviceList = new ArrayList<>();
            callPost(url);
        }else{
            logger.info("song or device is empty" + songId +" : "+activeDevice);
        }
    }

    private ArrayList<String> getAtiveDeviceID() {
        logger.info("calling active device");
        String url = "https://api.spotify.com/v1/me/player/devices";
        ArrayList<String> deviceList=new ArrayList<>();
        JSONObject jsonresponse = callGet(url);
        List elements = JsonPath.read(jsonresponse, "$.devices");
        for (Object element : elements) {
            deviceList.add(JsonPath.read(element, "$.id").toString());
        }
        checkActive(deviceList);
        activeDevice=deviceList.get(0);
        return deviceList;
    }

    public ArrayList<Track> getSearchData(String query) throws ParseException, MalformedURLException {

        query="q="+query;
        logger.info("calling Play query: "+query);
        String url = "https://api.spotify.com/v1/search?"+query+"&type=track&limit=20&market=IN";
        ArrayList<Track> songsList=new ArrayList<>();
        JSONObject jsonresponse = callGet(url);
        List elements = JsonPath.read(jsonresponse, "$.tracks.items");
        for (Object element : elements) {
           songsList.add(new Track(JsonPath.read(element, "$.uri").toString(),
                   JsonPath.read(element, "$.duration_ms"),
                   JsonPath.read(element, "$.popularity"),
                   JsonPath.read(element, "$.name")));
        }
        System.out.println(songsList.toString());
        return songsList;
    }
    public ArrayList<PlayList> getSearchPlayList(String query) {
        query="q="+query;
        logger.info("calling Play query: "+query);
        String url = "https://api.spotify.com/v1/search?"+query+"&type=playlist&limit=5&market=IN";

        ArrayList<PlayList> songsList=new ArrayList<>();
        JSONObject jsonresponse = callGet(url);
        List elements = JsonPath.read(jsonresponse, "$.playlists.items");
        for (Object element : elements) {
            songsList.add(new PlayList(JsonPath.read(element, "$.id").toString(),
                   JsonPath.read(element, "$.name").toString()));
        }
        System.out.println(songsList.toString());
        return songsList;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Accept", "application/json");
        headers.set("Authorization", "Bearer "+ token);
        return headers;
    }

    private JSONObject callPost(String url){

        HttpHeaders headers = getHeaders();
        HttpEntity request = new HttpEntity(headers);
        try{
            URL u = new URL(url);
            ResponseEntity<JSONObject> response = this.restTemplate.exchange(u.toString(), HttpMethod.POST, request, JSONObject.class, 1);
            return response.getBody();
        }catch (HttpClientErrorException exception){
            logger.info(exception.getLocalizedMessage());
            if(exception.getRawStatusCode()==401){
                getRefreshToken();
                callPost(url);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private JSONObject callGet(String url){

        HttpHeaders headers = getHeaders();
        HttpEntity request = new HttpEntity(headers);
        try{
            URL u = new URL(url);
            ResponseEntity<JSONObject> responseEntity=  this.restTemplate.exchange(u.toString(), HttpMethod.GET, request, JSONObject.class, 1);
            return responseEntity.getBody();
        }catch (HttpClientErrorException exception){
            logger.info(exception.getLocalizedMessage());
            if(exception.getRawStatusCode()==401){
                getRefreshToken();
                callGet(url);
            }
        }catch (MalformedURLException exception){
            logger.info(exception.getLocalizedMessage());
        }
        return null;
    }
    private void callPut(String url){
        HttpHeaders headers = getHeaders();
        HttpEntity request = new HttpEntity(headers);
        try{
            this.restTemplate.put(url,request);
        }catch (HttpClientErrorException exception){
            logger.info(exception.getLocalizedMessage());
            if(exception.getRawStatusCode()==401){
                getRefreshToken();
                callPut(url);
            }
        }
    }
    private void callPut(String url, JSONObject personJsonObject){
        HttpHeaders headers = getHeaders();
        HttpEntity<String> request = new HttpEntity<String>(personJsonObject.toString(), headers);
        try{
        this.restTemplate.put(url,request);
        }catch (HttpClientErrorException exception){
            logger.info(exception.getLocalizedMessage());
            if(exception.getRawStatusCode()==401){
                getRefreshToken();
                callPut(url,personJsonObject);
            }
        }
    }

    public void playList(String name) {
        tracks=getTrackList(searchPlayList(name));
        autoPlay(tracks);
    }

    private void autoPlay(Queue<Track> tracks) {
        currentPlayingTrack=tracks.remove();
        addToPlayList(currentPlayingTrack.getId());
        playNext();
    }

    private Queue<Track> getTrackList(String searchPlayList) {

        logger.info("calling playList track: "+searchPlayList);
        String url = "https://api.spotify.com/v1/playlists/"+searchPlayList+"/tracks?limit=20&market=IN";
        Queue<Track> songsList=new PriorityQueue<>();
        JSONObject jsonresponse = callGet(url);
        List elements = JsonPath.read(jsonresponse, "$.items");
        for (Object element : elements) {
            songsList.add(new Track(JsonPath.read(element, "$.track.uri").toString(),
                            JsonPath.read(element, "$.track.duration_ms"),
                            JsonPath.read(element, "$.track.popularity"),
                            JsonPath.read(element, "$.track.name")));
        }
        System.out.println(songsList.toString());
        return songsList;
    }

    private String searchPlayList(String name) {
      return   getSearchPlayList(name).get(0).getId();
    }

    @Override
    public void onStart() {
        logger.info("On Song start");
    }

    @Override
    public void onEnd() {
        logger.info("On Song ends");
        if(!tracks.isEmpty())
        {   currentPlayingTrack=tracks.remove();
            addToPlayList(currentPlayingTrack.getId());
            playNext();
        }
    }

    public void volume(String action){
        if(playerInfo!=null)
            volume=playerInfo.getVolume();
        else
            playerInfo();
        switch (action){
            case "down":
                if(volume>10)
                volume=volume-15;
                else volume=0;
                break;
            case "up":
                if(volume<90)
                volume=volume+15;
                else
                    volume=100;
                break;
        }
        volume(volume);
    }
    public void volume(int count) {
        logger.info("check active device");
        String device=activeDevice;
        String url =  "https://api.spotify.com/v1/me/player/volume?volume_percent="+count;
        callPut(url);
        playerInfo.setVolume(count);
    }
    public void playerInfo(){
        String url="https://api.spotify.com/v1/me/player";
        JSONObject res=callGet(url);
        PlayerInfo info=new PlayerInfo();
        info.setActive(JsonPath.read(res, "$.device.is_active"));
        info.setId(JsonPath.read(res, "$.device.id"));
        info.setVolume(JsonPath.read(res, "$.device.volume_percent"));
        info.setName(JsonPath.read(res, "$.device.name"));
        info.setPlaying(JsonPath.read(res, "$.is_playing"));
        playerInfo=info;
    }
}
