package com.crisper.server.oldImpl.utils;

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
        String token=UUID.randomUUID().toString();
        add(token);
        return token;
    }
}
