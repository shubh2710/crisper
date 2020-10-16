package com.crisper.server;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

public class TestMain2 {

        private static DatagramSocket socket = null;

        public static void main(String[] args) throws SocketException {
            new MulticastReceiver().start();
        }


}
