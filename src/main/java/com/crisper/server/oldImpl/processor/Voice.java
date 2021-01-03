package com.crisper.server.oldImpl.processor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Voice {
    @Value("${app.voice.url}")
    String voiceUrl;
    @Value("${app.python.version}")
    String python;
    public void say(String text){
        Runtime runtime = Runtime.getRuntime();

        try {
            System.out.println(python+" "+voiceUrl+" "+text);
            Process process =runtime.exec(python+" "+voiceUrl+" "+text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void delaySay(String text,String location) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process =runtime.exec(python+" "+voiceUrl+" "+text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
