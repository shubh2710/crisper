package com.crisper.server.voiceProcessor.impl;

import com.crisper.server.voiceProcessor.IVoiceProcessorService;
import com.crisper.server.voiceProcessor.model.VoiceRequest;
import com.crisper.server.voiceProcessor.model.VoiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoiceProcessorService implements IVoiceProcessorService {

    @Autowired
    PythonVoiceService voiceService;

    @Override
    public VoiceResponse process(VoiceRequest request) {
        VoiceResponse response=VoiceResponse.getObject();
        try {
            voiceService.say(request.getText());
            response.setProcessed(true);
            response.setText(request.getText());
        }catch (Exception e){
            response.setProcessed(false);
            response.setText(request.getText());
            response.setErrorMsg(e.getMessage());
        }
        return response;
    }
}
