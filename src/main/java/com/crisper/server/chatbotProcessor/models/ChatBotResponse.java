package com.crisper.server.chatbotProcessor.models;

import com.crisper.server.processors.models.ProcessRequest;

import java.util.List;
import java.util.Map;

public class ChatBotResponse {
    long id;
    String textRequestMsg;
    String textResponceMsg;
    Map<String,Object> slots;
    Map<String,String> missingSlots;
    String intent;
    List<String> patterns;
    String context;
    String type;
    boolean isError;
    String processType;
    private ProcessRequest processRequest;


    public ProcessRequest getProcessRequest() {
        return processRequest;
    }

    public void setProcessRequest(ProcessRequest processRequest) {
        this.processRequest = processRequest;
    }

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

    public Map<String, Object> getSlots() {
        return slots;
    }

    public void setSlots(Map<String, Object> slots) {
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

    public static ChatBotResponse getObject(){
        return new ChatBotResponse();
    }
}
