package com.crisper.server.chatbotProcessor.impl;

import com.crisper.server.chatbotProcessor.IChatBot;
import com.crisper.server.chatbotProcessor.IChatBotProcessor;
import com.crisper.server.chatbotProcessor.models.ChatBotRequest;
import com.crisper.server.chatbotProcessor.models.ChatBotResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
public class ChatBotProcessor  implements IChatBotProcessor {

    @Autowired
    private ApplicationContext context;

    IChatBot chatBot;

    @PostConstruct
    public void init(){
        chatBot=context.getBean(PythonChatBot.class);
    }
    @Override
    public ChatBotResponse process(ChatBotRequest request) {
        return chatBot.giveResponse(request);
    }
}
