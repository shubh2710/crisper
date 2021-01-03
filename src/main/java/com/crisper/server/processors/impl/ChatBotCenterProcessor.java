package com.crisper.server.processors.impl;

import com.crisper.server.chatbotProcessor.IChatBotProcessor;
import com.crisper.server.chatbotProcessor.models.ChatBotRequest;
import com.crisper.server.chatbotProcessor.models.ChatBotResponse;
import com.crisper.server.googleSearch.IGoogleSearchService;
import com.crisper.server.googleSearch.model.GoogleSearchRequest;
import com.crisper.server.googleSearch.model.GoogleSearchResponse;
import com.crisper.server.microcontroller.IMicrocontrollerProcessorService;
import com.crisper.server.microcontroller.model.MicrocontrollerRequest;
import com.crisper.server.microcontroller.model.MicrocontrollerResponse;
import com.crisper.server.processors.IChatbotCenterProcessor;
import com.crisper.server.processors.models.ProcessRequest;
import com.crisper.server.voiceProcessor.IVoiceProcessorService;
import com.crisper.server.voiceProcessor.model.VoiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatBotCenterProcessor implements IChatbotCenterProcessor {
    @Autowired
    IChatBotProcessor chatBotProcessor;

    @Autowired
    IGoogleSearchService googleSearchService;

    @Autowired
    IVoiceProcessorService voiceProcessorService;

    @Autowired
    IMicrocontrollerProcessorService microcontrollerProcessorService;
    @Override
    public ChatBotResponse process(ChatBotRequest request) {
        ChatBotResponse response= chatBotProcessor.process(request);
        processRequest(response.getProcessRequest());

        return response;
    }

    private void processRequest(ProcessRequest processRequest) {
        switch (processRequest.getProcessor()){
            case "music":
            case "voice":
                voiceProcessorService.process((VoiceRequest) processRequest.getProcessorRequest());
                break;
            case "search":
                GoogleSearchResponse response=googleSearchService.process((GoogleSearchRequest) processRequest.getProcessorRequest());
                processRequest(response.getVoiceRequest());
                break;
            case "microcontroller":
                MicrocontrollerResponse mcResponse=microcontrollerProcessorService.process((MicrocontrollerRequest) processRequest.getProcessorRequest());
                processRequest(mcResponse.getVoiceRequest());
            default:
        }
    }
}
