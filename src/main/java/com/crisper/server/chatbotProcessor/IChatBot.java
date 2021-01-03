package com.crisper.server.chatbotProcessor;

import com.crisper.server.chatbotProcessor.models.ChatBotRequest;
import com.crisper.server.chatbotProcessor.models.ChatBotResponse;
import org.springframework.stereotype.Component;

public interface IChatBot {
    public ChatBotResponse giveResponse(ChatBotRequest request);
}
