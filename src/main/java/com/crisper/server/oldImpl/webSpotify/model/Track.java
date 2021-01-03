package com.crisper.server.oldImpl.webSpotify.model;

public class Track implements   Comparable{
    String id;
    int duration;
    int popularity;
    String name;

    public Track(String id, int duration, int popularity,String name) {
        this.id = id;
        this.name=name;
        this.duration = duration;
        this.popularity = popularity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

}
