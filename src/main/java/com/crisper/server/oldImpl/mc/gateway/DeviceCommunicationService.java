package com.crisper.server.oldImpl.mc.gateway;


import com.crisper.server.oldImpl.utils.RestHttpCall;
import com.crisper.server.oldImpl.utils.MCTokenMap;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DeviceCommunicationService {

    @Autowired
    RestHttpCall restCall;

    public boolean turn(String name,String action){
        int value=getMapping(name);
        if(value==-1){
            return false;
        }
        String command="cmd_esp0_device_"+action+"_"+value+"_op_180_relay1";
        command=command.toLowerCase();
        System.out.println(command);
        try {
            JSONObject object= restCall.callGet("http://myesp0.local/device/command?command="+command+"&auth="+MCTokenMap.getToken(),null);
            System.out.println(object.toJSONString());
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private int getMapping(String name) {
        Map<String,Integer> settings=new HashMap<>();
        settings.put("ALEXA",1);
        settings.put("LIGHT",4);

        return settings.getOrDefault(name, -1);

    }

}
