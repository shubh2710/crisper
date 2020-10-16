package com.crisper.server.player.playerInterface;

public interface IMediaController {

    public boolean Play(String url);
    public boolean stop();
    public boolean playAgain();
    public boolean playNext();
    public boolean autoPlay();
    public boolean autoPlay(String name);
    public boolean playList(String root);
    public boolean isPlaying();
    public boolean playRandom();
    public boolean playDirectory(String name);
}
