/*

package com.crisper.server.player;

import com.crisper.server.fileManager.FileContoller;
import com.crisper.server.player.playerInterface.IAutoPlay;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class testFile implements IAutoPlay{
    SimpleAudioPlayerTest player;
    private int index=0;
    FileContoller fileContoller=new FileContoller("D:\\MP3");
    List<File> autoPlayList;
    public void runThis(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                player=new SimpleAudioPlayerTest();
                player.run();
            }
        }).start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void play(String s){
        ArrayList<File> list= fileContoller.searchSongsInDirectory(s);
        player.playSong(list.get(0).getAbsolutePath());
    }

    public void stop(){
        player.stop();
    }
    private void autoPlay(String nextLine) {
        List<File> list= fileContoller.searchFilesByDirectory();
        autoPlayList=list.subList(0,list.size());
        player.playSong(list.get(0).getAbsolutePath(),  this);
    }

    private void playNextSong() {
        index++;
        player.playSong(autoPlayList.get(index).getAbsolutePath(),  this);
    }
    public static void main(String[] args) throws InterruptedException {

        testFile tf=new testFile();
        tf.runThis();
        Scanner sc=new Scanner(System.in);
       while(true){
          String input=sc.nextLine();
          switch (input){
              case "STOP":
                  tf.stop();
                  break;
              case "PLAYNEXT":
                  tf.playNextSong();
                  break;
              case "PLAY":
                  tf.play(sc.nextLine());
                  break;
              case "PLAYDIRECTORY":
                  tf.autoPlay(sc.nextLine());
                  break;
          }
       }
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

*/
