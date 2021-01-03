package com.crisper.server.chatbotProcessor.impl;

import com.crisper.server.chatbotProcessor.IChatBot;
import com.crisper.server.chatbotProcessor.models.ChatBotRequest;
import com.crisper.server.chatbotProcessor.models.ChatBotResponse;
import com.crisper.server.googleSearch.model.GoogleSearchRequest;
import com.crisper.server.microcontroller.model.MicrocontrollerRequest;
import com.crisper.server.processors.models.ProcessRequest;
import com.crisper.server.voiceProcessor.model.VoiceRequest;
import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.configuration.BotConfiguration;
import org.alicebot.ab.model.History;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Scanner;

@Service
public class JavaChatBot implements IChatBot {
    static String botName = "crisper";
    Chat chatSession;
    @Value("${app.bot.url}")
    String botUrl;

    @PostConstruct
    public  void init() {
        System.out.println(botUrl);
        Bot bot = new Bot(BotConfiguration.
                builder()
                .name(botName)
                .path(botUrl)
                .enableNetworkConnection(false).build());
        chatSession = new Chat(bot);
        bot.getBrain().nodeStats();
        //  bot.writeQuit();
    }

    @Override
    public ChatBotResponse giveResponse(ChatBotRequest request) {
        ChatBotResponse response=ChatBotResponse.getObject();
        System.out.println("STATE=" + request.getTextRequestMsg() + ":THAT=" + ((History) chatSession.getThatHistory().get(0)).get(0) + ":TOPIC=" + chatSession.getPredicates().get("topic"));
        String textResponse=chatSession.multisentenceRespond(request.getTextRequestMsg());
        while (textResponse.contains("&lt;"))
            textResponse = textResponse.replace("&lt;", "<");
        while (textResponse.contains("&gt;"))
            textResponse = textResponse.replace("&gt;", ">");
        System.out.println("Robot : " + textResponse);
        // create Response
        response=createResponse(textResponse,response);
        response.setTextResponceMsg(textResponse);
        return response;
    }

    private ChatBotResponse createResponse(String data, ChatBotResponse response) {
        ChatBotResponse chatBotResponse =ChatBotResponse.getObject();
        ProcessRequest processRequest=null;

        String dataForVoice="";
        String dataToProcess ="";
            if(data.contains("<oob>")){
                dataForVoice=data.substring(0,data.indexOf("<oob>"));
                if(data.contains("<url>")){
                    dataToProcess =data.substring(data.indexOf("<url>")+5,data.indexOf("</url>"));
                    System.out.println(dataToProcess);
                    // music
                    ProcessRequest request=ProcessRequest.getMusicRequest();


                    //request.setProcessorRequest();

                    /*if(isOnline){
                        onlineActionService.takeAction(dataToProcess);
                    }else
                        actionService.takeAction(dataToProcess);
*/
                }else if(data.contains("<search>")){

                    dataToProcess =data.substring(data.indexOf("<search>")+8,data.indexOf("</search>"));
                    GoogleSearchRequest request=GoogleSearchRequest.getObject();
                    request.setTextToSearch(dataToProcess);
                    processRequest=ProcessRequest.getGoogleSearchRequest();

                    processRequest.setProcessorRequest(request);
                    chatBotResponse.setProcessRequest(processRequest);

                }else if(data.contains("<map>")){

                    dataToProcess =data.substring(data.indexOf("<map>")+5,data.indexOf("</map>"));
                    GoogleSearchRequest request=GoogleSearchRequest.getObject();
                    request.setTextToSearch(dataToProcess);
                    processRequest=ProcessRequest.getGoogleSearchRequest();
                    processRequest.setProcessorRequest(request);
                    chatBotResponse.setProcessRequest(processRequest);


                }else if(data.contains("<home>")){

                    MicrocontrollerRequest request=MicrocontrollerRequest.getObject();
                    filValues(request,data);
                    processRequest=ProcessRequest.getMCRequest();
                    processRequest.setProcessorRequest(request);
                    chatBotResponse.setProcessRequest(processRequest);

                }else if(data.contains("<volume>")){
                // adjust volume

                    /*dataToProcess =data.substring(data.indexOf("<volume>")+8,data.indexOf("</volume>"));
                    onlineActionService.takeAction(dataToProcess);
*/
                }
            }else{
                VoiceRequest voiceRequest=VoiceRequest.getObject();
                voiceRequest.setText(data);
                processRequest=ProcessRequest.getVoiceRequest();
                processRequest.setProcessorRequest(voiceRequest);
                chatBotResponse.setProcessRequest(processRequest);
            }
            return chatBotResponse;

    }

    private void filValues(MicrocontrollerRequest request, String data) {
        String dataToProcess =data.substring(data.indexOf("<home>")+6,data.indexOf("</home>"));
        String dataToSpeak =data.substring(0,data.indexOf("<home>")-5);

        dataToProcess=dataToProcess.toUpperCase();
        if(dataToProcess.contains("AUTOMATION")){
            String[] slots=dataToProcess.split("_");
            request.setArea("kitchen");
            request.setAction(slots[1]);
            request.setDevice(slots[2]);
        }
        ProcessRequest processRequest=ProcessRequest.getVoiceRequest();
        VoiceRequest voiceRequest=VoiceRequest.getObject();
        voiceRequest.setText(dataToSpeak);
        processRequest.setProcessorRequest(voiceRequest);
        request.setVoiceRequest(processRequest);
    }
}
