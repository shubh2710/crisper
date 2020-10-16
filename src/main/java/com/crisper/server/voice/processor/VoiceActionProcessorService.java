package com.crisper.server.voice.processor;


import com.crisper.server.voice.processor.service.OnlineMusicProcessActionService;
import com.crisper.server.voice.processor.service.ProcessActionService;
import com.crisper.server.web.service.GoogleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*ChromeDriver driver = new ChromeDriver();
 System.setProperty("webdriver.chrome.driver", "/usr/bin/google-chrome");
 // And now use this to visit Google
 driver.get("http://www.google.com");*/
@Component
public class VoiceActionProcessorService {


    @Autowired
    ProcessActionService actionService;

    @Autowired
    OnlineMusicProcessActionService onlineActionService;

    @Autowired
    GoogleSearchService googleSearchService;

    public static boolean isOnline=true;

    String dataToProcess ="";
        public void say(String data) throws Exception
        {
            String dataForVoice="";
            if(data.contains("<oob>")){
                dataForVoice=data.substring(0,data.indexOf("<oob>"));
                if(data.contains("<url>")){
                    dataToProcess =data.substring(data.indexOf("<url>")+5,data.indexOf("</url>"));
                    System.out.println(dataToProcess);

                    if(isOnline){
                        onlineActionService.takeAction(dataToProcess);
                    }else
                    actionService.takeAction(dataToProcess);
                }else if(data.contains("<search>")){
                    dataToProcess =data.substring(data.indexOf("<search>")+8,data.indexOf("</search>"));
                    dataForVoice=googleSearchService.search(dataToProcess);
                }else if(data.contains("<map>")){
                    dataToProcess =data.substring(data.indexOf("<map>")+5,data.indexOf("</map>"));
                    dataForVoice=googleSearchService.search(dataToProcess);
                }
            }else{
                dataForVoice=data;
            }
            Voice.Say(dataForVoice);
        }
}
