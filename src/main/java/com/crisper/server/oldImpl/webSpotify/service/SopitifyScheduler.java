package com.crisper.server.oldImpl.webSpotify.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;
import java.util.TimerTask;

public class SopitifyScheduler {
    Logger logger = LoggerFactory.getLogger(SopitifyScheduler.class);
    static Timer timer;
    ISpotifyNotifer notifer;
    Scheduler s;
        public void schedule(ISpotifyNotifer notifer, int ms) {
            timer = new Timer();
            this.notifer=notifer;
            this.notifer.onStart();
            Scheduler s=new Scheduler();
            timer.schedule(s, ms);
        }
        public void cancel(){
            try {
            timer.cancel();
            s.cancel();
            }catch (Exception e){
                logger.error(e.getMessage());
            }
        }
        class Scheduler extends TimerTask {
            public void run() {
                notifer.onEnd();

                timer.cancel();
            }
        }
}
