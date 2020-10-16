package com.crisper.server.web.service;

import com.crisper.server.voice.processor.Chatbot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoiceCommandService {

    @Autowired
    Chatbot chatbot;

    public void command(String request) throws Exception {
        chatbot.command(request);
    }

}
