package com.crisper.server;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class TestMain {

        private static DatagramSocket socket = null;

        public static void main(String[] args) throws IOException {

            //broadcast(InetAddress.getLocalHost().getHostAddress(), InetAddress.getByName("255.255.255.255"));

        }

        public static void broadcast(
                String broadcastMessage, InetAddress address) throws IOException {
            socket = new DatagramSocket();
            socket.setBroadcast(true);

            byte[] buffer = broadcastMessage.getBytes();

            DatagramPacket packet
                    = new DatagramPacket(buffer, buffer.length, address, 4445);
            socket.send(packet);
            socket.close();
        }
}
