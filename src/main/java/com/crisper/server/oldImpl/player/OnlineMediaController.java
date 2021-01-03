
package com.crisper.server.oldImpl.player;

import com.crisper.server.oldImpl.fileManager.IFileContoller;
import com.crisper.server.oldImpl.player.playerInterface.IMediaController;
import com.crisper.server.oldImpl.webSpotify.service.spotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
