package com.crisper.server.voiceProcessor;


import com.crisper.server.voiceProcessor.model.VoiceRequest;
import com.crisper.server.voiceProcessor.model.VoiceResponse;

public interface IVoiceProcessorService {

    public VoiceResponse process(VoiceRequest request);
}
