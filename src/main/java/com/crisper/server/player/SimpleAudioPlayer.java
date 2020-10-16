package com.crisper.server.player;// Java program to play an Audio
// file using Clip Object 

import com.crisper.server.player.playerInterface.IAutoPlay;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;


@Component
public class SimpleAudioPlayer extends Application implements Runnable{

    public static MediaPlayer mediaPlayer;
    static boolean  isPlaying=false;


    static String p;

    @PostConstruct
    public void SimpleAudioPlayer(){
        new Thread(this).start();
    }
    @Override
    public void start(Stage primaryStage) {

    }


    public void stop(){
        if(mediaPlayer!=null && isPlaying){
            mediaPlayer.stop();
            isPlaying=false;
        }
    }
    public boolean playSong(String path, IAutoPlay autoPlay){

        try {
            stop();
            p=new File(path).toURI().toString();
            Media hit = new Media(p);
            mediaPlayer = new MediaPlayer(hit);
            mediaPlayer.setVolume(mediaPlayer.getVolume()/7);
            mediaPlayer.setOnEndOfMedia(new Runnable() {
                @Override
                public void run() {
                    isPlaying = false;
                    autoPlay.onSongEnd();
                }
            });

            mediaPlayer.setOnReady(new Runnable() {
                @Override
                public void run() {
                    mediaPlayer.play();
                    isPlaying = true;
                    autoPlay.onSongStart();
                }
            });
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            autoPlay.onSongEnd();
            return false;
        }
    }

    @Override
    public void run() {
        launch(new String[1]);
    }
}