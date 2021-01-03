package com.crisper.server.googleSearch.model;

import com.crisper.server.processors.models.ProcessRequest;
import com.crisper.server.voiceProcessor.model.VoiceRequest;

public class GoogleSearchResponse {

    String textToSearch;
    private ProcessRequest voiceRequest;

    public ProcessRequest getVoiceRequest() {
        return voiceRequest;
    }

    public void setVoiceRequest(ProcessRequest voiceRequest) {
        this.voiceRequest = voiceRequest;
    }

    public static GoogleSearchResponse getObject() {
        return new GoogleSearchResponse();
    }

    public String getTextToSearch() {
        return textToSearch;
    }

    public void setTextToSearch(String textToSearch) {
        this.textToSearch = textToSearch;
    }
}
