package com.crisper.server.microcontroller.utils;

import org.json.simple.JSONObject;
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
import java.util.Collections;

@Service
public class RestCall {

    Logger logger = LoggerFactory.getLogger(RestCall.class);

    RestTemplate restTemplate=new RestTemplate();
    public JSONObject callPost(String url,String token){

        HttpHeaders headers = getHeaders(token);
        HttpEntity request = new HttpEntity(headers);
        try{
            URL u = new URL(url);
            ResponseEntity<JSONObject> response = this.restTemplate.exchange(u.toString(), HttpMethod.POST, request, JSONObject.class, 1);
            return response.getBody();
        }catch (HttpClientErrorException exception){
            logger.info(exception.getLocalizedMessage());
            if(exception.getRawStatusCode()==401){
                //getRefreshToken();
                //callPost(url);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public JSONObject callPost(String url,JSONObject personJsonObject,String token){

        HttpHeaders headers = getHeaders(token);
        HttpEntity<JSONObject> request = new HttpEntity<JSONObject>(personJsonObject, headers);
        try{
            URL u = new URL(url);
            ResponseEntity<JSONObject> response = this.restTemplate.exchange(u.toString(), HttpMethod.POST, request, JSONObject.class, 1);
            return response.getBody();
        }catch (HttpClientErrorException exception){
            logger.info(exception.getLocalizedMessage());
            if(exception.getRawStatusCode()==401){
                //getRefreshToken();
                //callPost(url);
            }
        } catch (MalformedURLException e) {
            //e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
        return null;
    }
    public JSONObject callGet(String url,String token){

        HttpHeaders headers = getHeaders(token);
        HttpEntity request = new HttpEntity(headers);
        try{
            URL u = new URL(url);
            ResponseEntity<JSONObject> responseEntity=  this.restTemplate.exchange(u.toString(), HttpMethod.GET, request, JSONObject.class, 1);
            return responseEntity.getBody();
        }catch (HttpClientErrorException exception){
            logger.info(exception.getLocalizedMessage());
            if(exception.getRawStatusCode()==401){
                //getRefreshToken();
                //callGet(url);
            }
        }catch (MalformedURLException exception){
            logger.info(exception.getLocalizedMessage());
        }
        return null;
    }
    public void callPut(String url,String token){
        HttpHeaders headers = getHeaders(token);
        HttpEntity request = new HttpEntity(headers);
        try{
            this.restTemplate.put(url,request);
        }catch (HttpClientErrorException exception){
            logger.info(exception.getLocalizedMessage());
            if(exception.getRawStatusCode()==401){
                //getRefreshToken();
                //callPut(url,token);
            }
        }
    }


    private HttpHeaders getHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Accept", "application/json");
        headers.set("content-type", "application/json");
        headers.set("Authorization", "Bearer "+ token);
        return headers;
    }

    public void callPut(String url, JSONObject personJsonObject,String token){
        HttpHeaders headers = getHeaders(token);
        HttpEntity<String> request = new HttpEntity<String>(personJsonObject.toString(), headers);
        try{
            this.restTemplate.put(url,request);
        }catch (HttpClientErrorException exception){
            logger.info(exception.getLocalizedMessage());
            if(exception.getRawStatusCode()==401){
                //getRefreshToken();
                //callPut(url,personJsonObject,token);
            }
        }
    }
    public String getRefreshToken(String refreshToken,String token) {

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
        return token;
    }
}
