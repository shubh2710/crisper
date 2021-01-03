package com.crisper.server.microcontroller.impl;

import com.crisper.server.microcontroller.IMicrocontrollerProcessorService;
import com.crisper.server.microcontroller.model.MCResponse;
import com.crisper.server.microcontroller.model.MicrocontrollerRequest;
import com.crisper.server.microcontroller.model.MicrocontrollerResponse;
import com.crisper.server.processors.models.ProcessRequest;
import com.crisper.server.voiceProcessor.model.VoiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MicrocontrollerService implements IMicrocontrollerProcessorService {

    @Autowired
    MicrocontrollerComService communicationService;

    @Override
    public MicrocontrollerResponse process(MicrocontrollerRequest request) {
        MicrocontrollerResponse response=MicrocontrollerResponse.getObject();

        try{
            MCResponse mcResponse=communicationService.turn(request.getArea(),request.getDevice(),request.getAction());
            response.setProcessed(mcResponse.isProcessed());
            response.setVoiceRequest(request.getVoiceRequest());
            if(!response.isProcessed()){
                ProcessRequest processRequest=ProcessRequest.getVoiceRequest();
                VoiceRequest voiceRequest=VoiceRequest.getObject();
                voiceRequest.setText(mcResponse.getErrorToSpeak());
                processRequest.setProcessorRequest(voiceRequest);

                response.setVoiceRequest(processRequest);
            }
        }catch (Exception e){
                response.setProcessed(false);

                ProcessRequest processRequest=ProcessRequest.getVoiceRequest();
                VoiceRequest voiceRequest=VoiceRequest.getObject();
                voiceRequest.setText("something went Wrong");
                processRequest.setProcessorRequest(voiceRequest);

                response.setVoiceRequest(processRequest);
        }
        return response;
    }
}
