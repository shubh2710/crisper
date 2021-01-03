package com.crisper.server.microcontroller.impl;


import com.crisper.server.microcontroller.model.MCResponse;
import com.crisper.server.microcontroller.utils.MCTokenMap;
import com.crisper.server.microcontroller.utils.RestCall;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class MicrocontrollerComService {

    @Autowired
    RestCall restCall;

    public MCResponse turn(String area, String name, String action){
        MCResponse response=MCResponse.getObject();
        int value=getMapping(name);
        if(value==-1){
            response.setErrorMsg("MappingNotFound");
            response.setErrorToSpeak("Device not found, Kindly check device configuration");
            response.setProcessed(false);
            return response;
        }
        //String command="cmd_"+area+"_device_"+action+"_"+value+"_op_180_relay1";
        String command="cmd_esp0_device_"+action+"_"+value+"_op_180_relay1";
        command=command.toLowerCase();
        System.out.println(command);
        try {
            JSONObject object= restCall.callGet("http://myesp0.local/device/command?command="+command+"&auth="+ MCTokenMap.getToken(),null);
            System.out.println(object.toJSONString());
            response.setProcessed(true);
        }catch (Exception e){
            e.printStackTrace();

            response.setErrorMsg("ServiceUnavalibale");
            response.setErrorToSpeak("Selected Area device is not available");
            response.setProcessed(false);
            return response;
        }
        return response;
    }

    private int getMapping(String name) {
        name=name.toUpperCase();
        Map<String,Integer> settings=new HashMap<>();
        settings.put("ALEXA",1 );
        settings.put("LIGHT",4);
        return settings.getOrDefault(name, -1);

    }

}
