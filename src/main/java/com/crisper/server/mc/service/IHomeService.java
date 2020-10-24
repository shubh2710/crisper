package com.crisper.server.mc.service;

public interface IHomeService {
    public void turnOn(String name,String room);
    public void turnOff(String name,String room);
    public void setSpeed(String name,String room,int number);
    public String takeAction(String data);
}
