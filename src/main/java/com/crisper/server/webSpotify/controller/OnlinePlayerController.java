package com.crisper.server.webSpotify.controller;

import com.crisper.server.webSpotify.model.Token;
import com.crisper.server.webSpotify.service.spotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class OnlinePlayerController {

    @Autowired
    spotifyService spotifyService;

    @GetMapping
    public String processForm(@RequestParam("code") String code)
    {
        System.out.println(code);
        spotifyService.setRefreshToken(code);
        return "redirect:/auth.html";
    }
    @PostMapping("/token")
    public  void saveToken(@RequestBody Token token)
    {
        System.out.println(token.getToken());
        spotifyService.setRefreshToken(token.getToken());
    }
}
