package com.crisper.server.microcontroller.model;

import com.crisper.server.processors.models.ProcessRequest;

import java.util.Map;

public class MicrocontrollerResponse {
    long id;
    Map<String,Object> slots;
    String action;
    String device;
    String area;
    int opacity;
    boolean isProcessed;
    ProcessRequest voiceRequest;
    String errorTxt;
    String errorMsg;

    public String getErrorTxt() {
        return errorTxt;
    }

    public void setErrorTxt(String errorTxt) {
        this.errorTxt = errorTxt;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static MicrocontrollerResponse getObject() {
        return new MicrocontrollerResponse();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Map<String, Object> getSlots() {
        return slots;
    }

    public void setSlots(Map<String, Object> slots) {
        this.slots = slots;
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

    public boolean isProcessed() {
        return isProcessed;
    }

    public void setProcessed(boolean processed) {
        isProcessed = processed;
    }

    public ProcessRequest getVoiceRequest() {
        return voiceRequest;
    }

    public void setVoiceRequest(ProcessRequest voiceRequest) {
        this.voiceRequest = voiceRequest;
    }
}
