package com.crisper.server.voiceProcessor.model;

public class VoiceRequest {
    int id;
    String text;

    public static VoiceRequest getObject() {
        return new VoiceRequest();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
