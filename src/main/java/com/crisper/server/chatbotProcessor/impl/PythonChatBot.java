package com.crisper.server.chatbotProcessor.impl;

import com.crisper.server.chatbotProcessor.IChatBot;
import com.crisper.server.chatbotProcessor.models.ChatBotRequest;
import com.crisper.server.chatbotProcessor.models.ChatBotResponse;
import com.crisper.server.microcontroller.model.MicrocontrollerRequest;
import com.crisper.server.microcontroller.utils.RestCall;
import com.crisper.server.processors.models.ProcessRequest;
import com.crisper.server.voiceProcessor.model.VoiceRequest;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PythonChatBot implements IChatBot {
    @Autowired
    RestCall restCall;
    @Override
    public ChatBotResponse giveResponse(ChatBotRequest request) {
        ChatBotResponse chatBotResponse=ChatBotResponse.getObject();
        try{
            JSONObject aiResponse=command(request.getTextRequestMsg());
            createResponce(chatBotResponse,aiResponse);
        }catch (Exception ex){

        }
        return chatBotResponse;
    }
    public JSONObject command(String data) throws Exception
    {
        JSONObject personJsonObject = new JSONObject();
        personJsonObject.put("command", data);
        JSONObject responce=restCall.callPost("http://localhost:5000/ai/v1/command",personJsonObject,"");
        System.out.println(responce);
        return responce;
    }


    private void createResponce(ChatBotResponse response, JSONObject data) {

        Map<String,Object> slots=(Map)data.get("slots");
        response.setType(data.get("actionType").toString());
        response.setSlots(slots);


        String dataToSpeak=data.get("responce").toString();

        switch (response.getType()){
            case "ask":
                createVoiceRequest(response,dataToSpeak);
            case "Err":
                createVoiceRequest(response,dataToSpeak);
            case "mc":
                createMCRequest(response,slots,dataToSpeak);
            case "googleSearch":
            case "music":

        }
    }

    private void createMCRequest(ChatBotResponse response,Map<String,Object> slots,String dataToSpeak) {
        ProcessRequest processRequest=ProcessRequest.getMCRequest();
        MicrocontrollerRequest request=MicrocontrollerRequest.getObject();
        request.setSlots(slots);
        request.setAction(slots.get("slotAction").toString());
        request.setArea(slots.get("slotRoomName").toString());
        request.setDevice(slots.get("slotDevice").toString());
        // create voice request
        request.setVoiceRequest(getVoiceProcessorRequest(dataToSpeak));
        processRequest.setProcessorRequest(request);
        response.setProcessRequest(processRequest);
    }

    private void createVoiceRequest(ChatBotResponse response,String dataToSpeak) {

        response.setProcessRequest(getVoiceProcessorRequest(dataToSpeak));
    }

    private ProcessRequest getVoiceProcessorRequest(String dataToSpeak) {
        ProcessRequest processRequest=ProcessRequest.getVoiceRequest();
        VoiceRequest voiceRequest=VoiceRequest.getObject();
        voiceRequest.setText(dataToSpeak);
        processRequest.setProcessorRequest(voiceRequest);
        return processRequest;
    }


}
