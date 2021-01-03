package com.crisper.server.microcontroller.impl;



import com.crisper.server.microcontroller.utils.MCTokenMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/device")
public class SerialController {

    @GetMapping("/{token}")
    public String validate(@PathVariable String token){
        if(MCTokenMap.find(token)){
            MCTokenMap.remove(token);
            return "OK";
        } else return "error";
    }
}


