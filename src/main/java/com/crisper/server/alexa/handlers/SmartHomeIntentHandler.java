package com.crisper.server.alexa.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.crisper.server.alexa.localization.LocalizationManager;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class SmartHomeIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("smartHome"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = LocalizationManager.getInstance().getMessage("HELLO_SMART");;
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("smartAns", speechText)
                .build();
    }

}