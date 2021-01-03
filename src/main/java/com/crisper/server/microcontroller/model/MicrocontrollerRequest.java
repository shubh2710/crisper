package com.crisper.server.microcontroller.model;

import com.crisper.server.processors.models.ProcessRequest;
import com.crisper.server.voiceProcessor.model.VoiceRequest;

import java.util.Map;

public class MicrocontrollerRequest {
    String command;
    Map<String,Object> slots;
    long id;
    String action;
    String device;
    String area;
    int opacity;
    ProcessRequest voiceRequest;

    public ProcessRequest getVoiceRequest() {
        return voiceRequest;
    }

    public void setVoiceRequest(ProcessRequest voiceRequest) {
        this.voiceRequest = voiceRequest;
    }

    public static MicrocontrollerRequest getObject() {
        return new MicrocontrollerRequest();
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Map<String, Object> getSlots() {
        return slots;
    }

    public void setSlots(Map<String, Object> slots) {
        this.slots = slots;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getOpacity() {
        return opacity;
    }

    public void setOpacity(int opacity) {
        this.opacity = opacity;
    }
}
