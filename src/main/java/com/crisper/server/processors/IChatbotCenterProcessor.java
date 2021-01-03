package com.crisper.server.processors;

import com.crisper.server.chatbotProcessor.models.ChatBotRequest;
import com.crisper.server.chatbotProcessor.models.ChatBotResponse;

public interface IChatbotCenterProcessor {
    public ChatBotResponse process(ChatBotRequest request);
}
