/*

package com.crisper.server.player;// Java program to play an Audio
// file using Clip Object 

import com.crisper.server.fileManager.FileContoller;
import com.crisper.server.player.playerInterface.IAutoPlay;
import com.crisper.server.voice.processor.Voice;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SimpleAudioPlayerTest extends Application {
    FileContoller fileContoller=new FileContoller("\"D:\\\\MP3\"");
    public static MediaPlayer mediaPlayer;
    static boolean  isPlaying=false;
    static boolean  isAutoPlaying=false;
    List<File> selectedFiles;
    int mediaNo=0;
    static String p;

    @Override
    public void start(Stage primaryStage) {

    }

    public ArrayList<File> getFiles(String dataToplay){
        return fileContoller.searchSongsInDirectory(dataToplay);
    }
    public List<File> getFiles(){
        return fileContoller.searchFilesByDirectory();
    }

    public void stop(){
        if(mediaPlayer!=null && isPlaying){
            mediaPlayer.stop();
            isPlaying=false;
        }
    }

    public boolean playSong(String path){

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
                }
            });

            mediaPlayer.setOnReady(new Runnable() {
                @Override
                public void run() {
                    mediaPlayer.play();
                    isPlaying = true;
                }
            });
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
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
            e.printStackTrace();
            return false;
        }
    }

    public void run() {
        launch(new String[1]);
    }
}
*/
