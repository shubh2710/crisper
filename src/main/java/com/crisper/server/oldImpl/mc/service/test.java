package com.crisper.server.oldImpl.mc.service;

public class test {

    public static void main(String[] aa){
        System.out.println(map(5,1,10,1,100));
    }
    public static double map(double x,double a,double b,double c,double d){
        return  (x-a)/(b-a) * (d-c) + c;
    }
}