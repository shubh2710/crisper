package com.crisper.server.chatbotProcessor;

import com.crisper.server.chatbotProcessor.models.ChatBotRequest;
import com.crisper.server.chatbotProcessor.models.ChatBotResponse;
import org.springframework.stereotype.Component;

public interface IChatBotProcessor {
    public ChatBotResponse process(ChatBotRequest request);
}
