package com.crisper.server.voiceProcessor.impl;

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
public class PythonVoiceService {

    @Autowired
    RestHttpCall restCall;

        public void say(String data) throws Exception
        {

            JSONObject personJsonObject = new JSONObject();
            personJsonObject.put("command", data);
            restCall.callPost("http://localhost:5000/voice/v1/say",personJsonObject,"");
        }
}
