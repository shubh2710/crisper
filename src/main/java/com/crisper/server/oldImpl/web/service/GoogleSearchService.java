package com.crisper.server.oldImpl.web.service;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

@Service
public class GoogleSearchService {
    public String search(String searchData) {
        String wrongResults[]={"Sorry no results found on internet.  Please try somthing else.",
                        "Unable to find results on internet. Please try somthing else.",
                "I tried google search but found nothing.Please try somthing else.",
                "Google might not want to give your answer."};
        String webResult="";
        try {
            JSONParser parser = new JSONParser();
            HttpTransport httpTransport = new NetHttpTransport();
            HttpRequestFactory requestFactory = httpTransport.createRequestFactory();
            GenericUrl url = new GenericUrl("https://kgsearch.googleapis.com/v1/entities:search");
            url.put("query", searchData);
            url.put("limit", "1");
            url.put("indent", "true");
            url.put("key", "AIzaSyCXDPb5MzSkl-8G6R_yTKlOx9duWNnsxNk");
            HttpRequest request = requestFactory.buildGetRequest(url);
            HttpResponse httpResponse = request.execute();
            JSONObject response = (JSONObject) parser.parse(httpResponse.parseAsString());
            //System.out.println(response.toJSONString());
            JSONArray elements = (JSONArray) response.get("itemListElement");
            for (Object element : elements) {
                webResult=JsonPath.read(element, "$.result.detailedDescription.articleBody").toString();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if(webResult.isEmpty()){
            return wrongResults[getRandomInteger(0,wrongResults.length-1)];
        }
        return webResult;
    }
    public static int getRandomInteger(int maximum, int minimum){
        return ((int) (Math.random()*(maximum - minimum))) + minimum;
    }

}
