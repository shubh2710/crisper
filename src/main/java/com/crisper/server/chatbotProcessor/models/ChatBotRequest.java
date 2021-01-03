package com.crisper.server.chatbotProcessor.models;

import java.util.List;
import java.util.Map;

public class ChatBotRequest {
    long id;
    String textRequestMsg;
    String textResponceMsg;
    Map<String,String> slots;
    Map<String,String> missingSlots;
    String intent;
    List<String> patterns;
    String context;
    String type;
    boolean isError;
    String processType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTextRequestMsg() {
        return textRequestMsg;
    }

    public void setTextRequestMsg(String textRequestMsg) {
        this.textRequestMsg = textRequestMsg;
    }

    public String getTextResponceMsg() {
        return textResponceMsg;
    }

    public void setTextResponceMsg(String textResponceMsg) {
        this.textResponceMsg = textResponceMsg;
    }

    public Map<String, String> getSlots() {
        return slots;
    }

    public void setSlots(Map<String, String> slots) {
        this.slots = slots;
    }

    public Map<String, String> getMissingSlots() {
        return missingSlots;
    }

    public void setMissingSlots(Map<String, String> missingSlots) {
        this.missingSlots = missingSlots;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public List<String> getPatterns() {
        return patterns;
    }

    public void setPatterns(List<String> patterns) {
        this.patterns = patterns;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }
    public static ChatBotRequest getObject(){
        return new ChatBotRequest();
    }
}
