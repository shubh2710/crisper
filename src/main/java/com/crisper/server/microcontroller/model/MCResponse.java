package com.crisper.server.microcontroller.model;

public class MCResponse {
    String errorMsg;
    String errorToSpeak;
    boolean isProcessed;

    public static MCResponse getObject() {
        return new MCResponse();
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorToSpeak() {
        return errorToSpeak;
    }

    public void setErrorToSpeak(String errorToSpeak) {
        this.errorToSpeak = errorToSpeak;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public void setProcessed(boolean processed) {
        isProcessed = processed;
    }
}
