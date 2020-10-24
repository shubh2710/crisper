
package com.crisper.server.player;

import com.crisper.server.fileManager.FileContoller;
import com.crisper.server.player.playerInterface.IAutoPlay;
import com.crisper.server.player.playerInterface.IMediaController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class MediaController implements IMediaController, IAutoPlay
{

    @Autowired
    SimpleAudioPlayer player;
    private int index=0;
    @Autowired
    FileContoller fileContoller;
    List<File> autoPlayList=new ArrayList<>();
    String currentSelectedSong="";

    @Override
    public boolean Play(String song) {
        ArrayList<File> list= fileContoller.searchSongsInDirectory(song);
        if(list.size()>0){
            currentSelectedSong=list.get(0).getAbsolutePath();
            player.playSong(currentSelectedSong,this);
        }
        return true;
    }

    @Override
    public boolean stop() {
        player.stop();
        index=0;
        return true;
    }

    @Override
    public boolean playAgain() {
        player.playSong(currentSelectedSong,this);
        return true;
    }

    @Override
    public boolean playNext() {
        index++;
        player.playSong(autoPlayList.get(index).getAbsolutePath(),  this);
        return true;
    }


    @Override
    public boolean autoPlay() {
        List<File> list= fileContoller.searchFilesByDirectory();
        autoPlayList=list.subList(0,list.size());
        player.playSong(list.get(0).getAbsolutePath(),  this);
        return true;
    }

    @Override
    public boolean autoPlay(String name) {
        autoPlayList.clear();
        List<File> list=fileContoller.getListByDirectoryName(name);
        if(list.size()>0){
            autoPlayList=list.subList(0,list.size());
            player.playSong(list.get(0).getAbsolutePath(),  this);
        }
        return false;
    }

    @Override
    public boolean playList(String root) {
        List<File> list= fileContoller.searchFilesByDirectory();
        autoPlayList=list.subList(0,list.size());
        player.playSong(list.get(0).getAbsolutePath(),  this);
        return true;
    }

    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public boolean playRandom() {
        return false;
    }

    @Override
    public boolean playDirectory(String name) {
        autoPlay(name);
        return false;
    }

    @Override
    public boolean volume(String count) {
        return false;
    }

    private void playNextSong() {
        index++;
        if(index<autoPlayList.size())
          player.playSong(autoPlayList.get(index).getAbsolutePath(),  this);
        else
            stop();
    }

    @Override
    public void onSongEnd() {
        System.out.println("on stop");
        playNextSong();
    }

    @Override
    public void onSongStart() {
        System.out.println("on start");
    }

    //new features
    // list avaliable songs
    // play selected or list of songs
    //upload song in server directory
    // set window volume
    //android text to voice
    //
}
