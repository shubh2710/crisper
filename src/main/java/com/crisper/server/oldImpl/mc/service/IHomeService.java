package com.crisper.server.oldImpl.mc.service;

public interface IHomeService {
    public String turnOn(String name,String room);
    public String turnOff(String name,String room);
    public String setSpeed(String name,String room,int number);
    public String takeAction(String data);
}
