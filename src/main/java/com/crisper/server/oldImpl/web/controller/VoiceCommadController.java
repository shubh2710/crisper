package com.crisper.server.oldImpl.web.controller;

import com.crisper.server.oldImpl.web.models.Command;
import com.crisper.server.oldImpl.web.service.VoiceCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/voice/")
public class VoiceCommadController {

    @Autowired
    VoiceCommandService service;

    @PostMapping
    public void command(@RequestBody Command command) throws Exception {
        service.command(command.getCommand().trim());
    }
    @PostMapping("withDelay")
    public void commandWithDelay(@RequestBody Command command) throws Exception {
        Thread.sleep(3000);
        service.command(command.getCommand().trim());
    }
}
