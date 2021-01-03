package com.crisper.server.oldImpl.processor;

import com.crisper.server.oldImpl.mc.service.IHomeService;
import com.crisper.server.oldImpl.utils.RestHttpCall;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*ChromeDriver driver = new ChromeDriver();
 System.setProperty("webdriver.chrome.driver", "/usr/bin/google-chrome");
 // And now use this to visit Google
 driver.get("http://www.google.com");*/
@Component
public class PythonVoiceActionProcessorService {

    @Autowired
    IHomeService homeService;


    @Autowired
    RestHttpCall restCall;

        public void say(String data) throws Exception
        {
            JSONObject personJsonObject = new JSONObject();
            personJsonObject.put("command", data);
            restCall.callPost("http://localhost:5000/voice/v1/say",personJsonObject,"");
        }
        public JSONObject command(String data) throws Exception
        {
            JSONObject personJsonObject = new JSONObject();
            personJsonObject.put("command", data);
            JSONObject responce=restCall.callPost("http://localhost:5000/ai/v1/command",personJsonObject,"");
            System.out.println(responce);
            return responce;
        }

    public String process(JSONObject responseFromAi) {
        JSONObject slots=(JSONObject)responseFromAi.get("slots");
        String action=slots.get("slotAction").toString();
        String device=slots.get("slotDevice").toString();
        String room=slots.get("slotRoomName").toString();
        //homeService.takeAction()
        return responseFromAi.get("responce").toString();
    }
}
