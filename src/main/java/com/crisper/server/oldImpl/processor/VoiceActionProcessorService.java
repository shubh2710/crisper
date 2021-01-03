package com.crisper.server.oldImpl.processor;

import com.crisper.server.oldImpl.mc.service.IHomeService;
import com.crisper.server.oldImpl.processor.service.OnlineMusicProcessActionService;
import com.crisper.server.oldImpl.processor.service.ProcessActionService;
import com.crisper.server.oldImpl.web.service.GoogleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
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
    IHomeService homeService;

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    GoogleSearchService googleSearchService;

    @Autowired
    Voice voice;

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
                }else if(data.contains("<home>")){
                    dataToProcess =data.substring(data.indexOf("<home>")+6,data.indexOf("</home>"));
                    String res=homeService.takeAction(dataToProcess);
                    if(res!=null) dataForVoice=res;
                }else if(data.contains("<volume>")){
                    dataToProcess =data.substring(data.indexOf("<volume>")+8,data.indexOf("</volume>"));
                    onlineActionService.takeAction(dataToProcess);
                }
            }else{
                dataForVoice=data;
            }
            voice.say(dataForVoice);
        }
}
