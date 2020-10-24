package com.crisper.server.alexa.servlet;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.amazon.ask.servlet.SkillServlet;
import com.crisper.server.alexa.handlers.*;
import com.crisper.server.alexa.interceptors.request.LocalizationRequestInterceptor;
import com.crisper.server.alexa.interceptors.request.LogRequestInterceptor;
import com.crisper.server.alexa.interceptors.response.LogResponseInterceptor;

public class AlexaServlet extends SkillServlet {

    public AlexaServlet() {
        super(getSkill());
    }

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new CancelandStopIntentHandler(),
                        new HelloWorldIntentHandler(),
                        new SmartHomeIntentHandler(),
                        new HelpIntentHandler(),
                        new LaunchRequestHandler(),
                        new SessionEndedRequestHandler(),
                        new FallbackIntentHandler(),
                        new ErrorHandler())
                .addExceptionHandler(new MyExceptionHandler())
                .addRequestInterceptors(
                        new LogRequestInterceptor(),
                        new LocalizationRequestInterceptor())
                .addResponseInterceptors(new LogResponseInterceptor())
                // Add your skill id below
                .withSkillId("amzn1.ask.skill.ce0bb04d-8d26-425d-aafc-1bd40eba6272")
                .build();
    }

}