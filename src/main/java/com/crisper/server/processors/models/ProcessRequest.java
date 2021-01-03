package com.crisper.server.processors.models;

public class ProcessRequest {
    private String processor;
    private Object processorRequest;

    public static ProcessRequest getVoiceRequest() {
        ProcessRequest request= new ProcessRequest();
        request.setProcessor("voice");
        return request;
    }
    public static ProcessRequest getMCRequest() {
        ProcessRequest request= new ProcessRequest();
        request.setProcessor("microcontroller");
        return request;
    }
    public static ProcessRequest getMusicRequest() {
        ProcessRequest request= new ProcessRequest();
        request.setProcessor("music");
        return request;
    }
    public static ProcessRequest getGoogleSearchRequest() {
        ProcessRequest request= new ProcessRequest();
        request.setProcessor("search");
        return request;
    }


    public String getProcessor() {
        return processor;
    }

    private void setProcessor(String processor) {
        this.processor = processor;
    }

    public Object getProcessorRequest() {
        return processorRequest;
    }

    public void setProcessorRequest(Object processorRequest) {
        this.processorRequest = processorRequest;
    }
}
