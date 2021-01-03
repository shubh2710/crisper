package com.crisper.server.microcontroller.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MCTokenMap {
    public static Map<String,String> tokens=new HashMap();
    public static boolean find(String token){
        if(tokens.get(token)!=null){
            return true;
        }else return false;
    }
    public static void add(String token){
        tokens.put(token,"");
    }
    public static void remove(String token){
        tokens.remove(token);
    }
    public static String getToken(){
        String server= null;
        try {
            server = "@"+ InetAddress.getLocalHost().getCanonicalHostName()+":8080";
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String token=UUID.randomUUID().toString();
        add(token);
        token=token+server;
        return token;
    }
}
