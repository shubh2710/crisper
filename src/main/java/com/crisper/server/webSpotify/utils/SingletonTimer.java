package com.crisper.server.webSpotify.utils;

import java.util.Timer;

public class SingletonTimer {
    private static Timer timer=null;
    public static Timer getTimmer(){
        if(timer==null){
            timer=new Timer();
            return timer;
        }else
            return timer;
    }
}
