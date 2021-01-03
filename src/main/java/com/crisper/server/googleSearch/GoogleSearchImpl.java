package com.crisper.server.googleSearch;

import com.crisper.server.googleSearch.model.GoogleSearchRequest;
import com.crisper.server.googleSearch.model.GoogleSearchResponse;
import com.crisper.server.processors.models.ProcessRequest;
import com.crisper.server.voiceProcessor.model.VoiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoogleSearchImpl implements IGoogleSearchService{
    @Autowired
    GoogleService googleSearchService;

    @Override
    public GoogleSearchResponse process(GoogleSearchRequest request) {
        String data=googleSearchService.search(request.getTextToSearch());
        GoogleSearchResponse response=GoogleSearchResponse.getObject();

        VoiceRequest voiceRequest=VoiceRequest.getObject();
        voiceRequest.setText(data);
        ProcessRequest processRequest=ProcessRequest.getVoiceRequest();
        processRequest.setProcessorRequest(voiceRequest);
        response.setVoiceRequest(processRequest);

        return response;
    }
}
