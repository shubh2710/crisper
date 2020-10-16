package com.crisper.server.voice.processor.service;

import com.crisper.server.player.MediaController;
import com.crisper.server.player.OnlineMediaController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OnlineMusicProcessActionService {

    @Autowired
    OnlineMediaController mediaController;


    public boolean takeAction(String data){

        data=data.toUpperCase();
        if(data.contains("STOPMUSIC")){
            mediaController.stop();
        }else if(data.contains("PLAYNEXT")){
            mediaController.playNext();
        }else if(data.contains("PLAYLIST")){
            data=data.replace("PLAYLIST","").trim().toLowerCase();
            mediaController.playDirectory(data);
        }
        else if(data.contains("AUTOPLAY")){

            data=data.replace("AUTOPLAY","").trim().toLowerCase();
            if(data.length()>0)
            mediaController.autoPlay(data);
            else mediaController.autoPlay();

        }else if(data.contains("PLAY")){

            data=data.replace("PLAY","").trim().toLowerCase();
            mediaController.Play(data);

        }else if(data.contains("")){

            data=data.replace("PLAY","").trim().toLowerCase();
            mediaController.Play(data);

        }else {

        }

        return true;
    }

}
