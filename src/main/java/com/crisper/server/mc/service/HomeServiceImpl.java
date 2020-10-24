package com.crisper.server.mc.service;

import com.crisper.server.webSpotify.service.spotifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HomeServiceImpl implements IHomeService {

    Logger logger = LoggerFactory.getLogger(spotifyService.class);

    @Override
    public void turnOn(String name, String room) {
        logger.info("Turning on" + name + ""+ room);
    }

    @Override
    public void turnOff(String name, String room) {
        logger.info("Turning off" + name + ""+ room);
    }

    @Override
    public void setSpeed(String name, String room, int number) {
        logger.info("Setting speed " + name + " "+ room+" "+ number);
    }

    @Override
    public String takeAction(String data) {
        data=data.toUpperCase();
        if(data.contains("STOPMUSIC")){

        }else if(data.contains("PLAYNEXT")){

        }else if(data.contains("PLAYLIST")){
            data=data.replace("PLAYLIST","").trim().toLowerCase();

        }
        else if(data.contains("AUTOPLAY")){


        }else if(data.contains("PLAY")){


        }else if(data.contains("")){

        }else {

        }
        return null;
    }

}
