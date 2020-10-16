package com.crisper.server.voice.processor;

import java.io.IOException;

public class Voice {
    public static void Say(String text){
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process =runtime.exec("wscript.exe D:\\projetcs\\server\\server\\lib\\master.vbs "+text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void delaySay(String text) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process =runtime.exec("wscript.exe D:\\projetcs\\server\\server\\lib\\master.vbs "+text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
