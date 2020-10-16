package com.crisper.server.webSpotify.service;

import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONArray;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class spotifyService {

    Logger logger = LoggerFactory.getLogger(spotifyService.class);
    private String refreshToken ="BQCne7lHRLPiGyKNuiv2WYI_cK7mwt79vyOLf0dpHaOIX7krc8JpTspzdPARyOtEWkrHpmYEHD-ArWOMDFj3rapSMI7btAZLAIeHx_dL1bvpMdmhf3pSP_IrDqmwTQ0w0h9E7o-pK2MM0viFpFCDGIct6f1abd80tiHD81bI6qU-IisI7I8w7SCWnKXapr1neOhpVM3Vdi-sIIlbjrzCKOoQDyJsYWS3W1nW_QqCY0JDpHiQDWMCzagcHXCA2cdgUGcG_TH_pGE-fRXWk4y4PPq_IFJiRTZaAUBvWTAky9UnrA";

    private String token="BQCne7lHRLPiGyKNuiv2WYI_cK7mwt79vyOLf0dpHaOIX7krc8JpTspzdPARyOtEWkrHpmYEHD-ArWOMDFj3rapSMI7btAZLAIeHx_dL1bvpMdmhf3pSP_IrDqmwTQ0w0h9E7o-pK2MM0viFpFCDGIct6f1abd80tiHD81bI6qU-IisI7I8w7SCWnKXapr1neOhpVM3Vdi-sIIlbjrzCKOoQDyJsYWS3W1nW_QqCY0JDpHiQDWMCzagcHXCA2cdgUGcG_TH_pGE-fRXWk4y4PPq_IFJiRTZaAUBvWTAky9UnrA";
    JSONParser parser = new JSONParser();
    RestTemplate restTemplate=new RestTemplate();
    String activeDevice="";

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
    }

    public void play(String name) {

        String query="q="+name;

        logger.info("calling Play query: "+query);
        String url = "https://api.spotify.com/v1/search?"+query+"&type=track&limit=20&market=IN";

        try {
            URL u = new URL(url) ;
            ArrayList<String> list=getSearchData(u.toString());
            ArrayList<String> activeDeviceList=getAtiveDeviceID();
            checkActive(activeDeviceList);
            addToPlayList(list);
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
    }

    public void playNext() {
        logger.info("Playing next");
        if(activeDevice.isEmpty()){
            getAtiveDeviceID();
        }
        String url =  "https://api.spotify.com/v1/me/player/next?device_id="+activeDevice;
        callPost(url);
    }

    private void addToPlayList(ArrayList<String> list) throws ParseException, MalformedURLException {
        logger.info("Add to play list");
        String song=list.get(0);
        if(!song.isEmpty() && !activeDevice.isEmpty()) {
            String url = "https://api.spotify.com/v1/me/player/queue?uri=" + song + "&device_id=" + activeDevice;
            URL u = new URL(url);
            ArrayList<String> deviceList = new ArrayList<>();
            callPost(url);
        }else{
            logger.info("song or device is empty" + song +" : "+activeDevice);
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
        activeDevice=deviceList.get(0);
        return deviceList;
    }

    public ArrayList<String> getSearchData(String url) throws ParseException {
        logger.info("searching song");
        ArrayList<String> songsList=new ArrayList<>();
        JSONObject jsonresponse = callGet(url);
        List elements = JsonPath.read(jsonresponse, "$.tracks.items");
        for (Object element : elements) {
           songsList.add(JsonPath.read(element, "$.uri").toString());
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
            ResponseEntity<JSONObject> response = this.restTemplate.exchange(url, HttpMethod.POST, request, JSONObject.class, 1);
            return response.getBody();
        }catch (HttpClientErrorException exception){
            logger.info(exception.getLocalizedMessage());
            if(exception.getRawStatusCode()==403){
                getRefreshToken();
                callPost(url);
            }
        }
        return null;
    }
    private JSONObject callGet(String url){
        HttpHeaders headers = getHeaders();
        HttpEntity request = new HttpEntity(headers);
        try{
            ResponseEntity<JSONObject> responseEntity=  this.restTemplate.exchange(url, HttpMethod.GET, request, JSONObject.class, 1);
            return responseEntity.getBody();
        }catch (HttpClientErrorException exception){
            logger.info(exception.getLocalizedMessage());
            if(exception.getRawStatusCode()==403){
                getRefreshToken();
                callGet(url);
            }
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
            if(exception.getRawStatusCode()==403){
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
            if(exception.getRawStatusCode()==403){
                getRefreshToken();
                callPut(url,personJsonObject);
            }
        }
    }
}
