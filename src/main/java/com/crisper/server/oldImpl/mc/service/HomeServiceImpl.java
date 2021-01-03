package com.crisper.server.oldImpl.mc.service;

import com.crisper.server.oldImpl.mc.gateway.DeviceCommunicationService;
import com.crisper.server.oldImpl.web.config.HomeProperties;
import com.crisper.server.oldImpl.webSpotify.service.spotifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeServiceImpl implements IHomeService {

    @Autowired
    DeviceCommunicationService communicationService;
    @Autowired
    HomeProperties homeProperties;
    Logger logger = LoggerFactory.getLogger(spotifyService.class);

    @Override
    public String turnOn(String name, String action) {
        if(communicationService.turn(name,action)){

        }else{
            return "Not able to process your request";
        }
        logger.info("Turning on" + name);
        return null;
    }

    @Override
    public String turnOff(String name, String action) {
        if(communicationService.turn(name,action)){

        }else{
            return "Not able to process your request";
        }
        logger.info("Turning off" + name);
        return null;
    }

    @Override
    public String setSpeed(String name, String room, int number) {
        logger.info("Setting speed " + name + " "+ room+" "+ number);
        return null;
    }

    @Override
    public String takeAction(String data) {

        System.out.println(homeProperties);

        data=data.toUpperCase();
        if(data.contains("AUTOMATION")){
            if(data.contains("ON")){
                String[] slots=data.split("_");
                return turnOn(slots[2],slots[1]);
            }

            if(data.contains("OFF")){
                String[] slots=data.split("_");
                return turnOff(slots[2],slots[1]);
            }
        }
        return "Sorry mapping not found to process request";
    }

    private void findAndGetAction(String data) {
        data.toLowerCase();
        if(data.contains("volume")){

        }else if(data.contains("light")){

        }


    }

}
