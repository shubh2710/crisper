package com.crisper.server.web.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;

@Component
public class scheduleBroadCase {

    @Scheduled(cron = "*/10 * * * * *")
    public void send() throws IOException {
        //System.out.println(InetAddress.getLocalHost().getCanonicalHostName());
        new MulticastPublisher().multicast("SERVERIP:"+InetAddress.getLocalHost().getCanonicalHostName());
    }
}
