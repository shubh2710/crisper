
package com.crisper.server.player;

import com.crisper.server.fileManager.FileContoller;
import com.crisper.server.player.playerInterface.IAutoPlay;
import com.crisper.server.player.playerInterface.IMediaController;
import com.crisper.server.webSpotify.service.spotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class OnlineMediaController implements IMediaController
{

    @Autowired
    spotifyService spotifyService;

    @Override
    public boolean Play(String song) {
        spotifyService.play(song);
        return true;
    }
    @Override
    public boolean stop() {
        spotifyService.stop();
        return true;
    }

    @Override
    public boolean playAgain() {

        return true;
    }

    @Override
    public boolean playNext() {
        spotifyService.playNextSong();
        return true;
    }


    @Override
    public boolean autoPlay() {

        return true;
    }

    @Override
    public boolean autoPlay(String name) {

        return false;
    }

    @Override
    public boolean playList(String root) {
        spotifyService.playList(root);
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

        return false;
    }

    @Override
    public boolean volume(String count) {
        spotifyService.volume(count);
        return true;
    }


}
