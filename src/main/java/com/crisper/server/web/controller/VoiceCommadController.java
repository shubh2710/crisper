package com.crisper.server.web.controller;

import com.crisper.server.web.models.Command;
import com.crisper.server.web.service.VoiceCommandService;
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
    public void commmand(@RequestBody Command command) throws Exception {
        service.command(command.getCommand());
    }
}
