package com.crisper.server.webController.controllers;

import com.crisper.server.chatbotProcessor.models.ChatBotRequest;
import com.crisper.server.oldImpl.web.models.Command;
import com.crisper.server.oldImpl.web.service.VoiceCommandService;
import com.crisper.server.processors.IChatbotCenterProcessor;
import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.configuration.BotConfiguration;
import org.alicebot.ab.model.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Scanner;

@RestController
@RequestMapping("crisper/v1/")
public class WebCommandController {

    @Autowired
    IChatbotCenterProcessor chatbotCenterProcessor;

    @PostMapping("exec")
    public void commandExec(@RequestBody Command command) throws Exception {
        ChatBotRequest request=ChatBotRequest.getObject();
        request.setTextRequestMsg(command.getCommand());
        chatbotCenterProcessor.process(request);
        "C:\\Program Files (x86)\\Java\\jdk1.8.0_261\\bin";
    }
    @PostMapping("voice")
    public void voiceExec(@RequestBody Command command) throws Exception {
    }

    @PostConstruct
    public void init(){
        new Thread(){
            @Override
            public void run() {
                try {
                    Scanner s=new Scanner(System.in);
                    String textLine = "";
                    while(true) {
                        System.out.print("Human : ");
                        textLine = s.nextLine();
                        if ((textLine == null) || (textLine.length() < 1))
                            System.out.println("null");
                        if (textLine.equals("q")) {
                            System.exit(0);
                        } else if (textLine.equals("wq")) {
                        } else {
                            String response="Some Error occurred";
                            try{
                                String request = textLine;

                                ChatBotRequest request1=ChatBotRequest.getObject();
                                request1.setTextRequestMsg(textLine);
                                chatbotCenterProcessor.process(request1);

                            }
                            catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}