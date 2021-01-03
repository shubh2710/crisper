package com.crisper.server.oldImpl.web.controller;

import com.crisper.server.oldImpl.web.models.Command;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/file/")
public class WebFileController {
    @GetMapping
    public void getFiles(@RequestBody Command command) throws Exception {

    }


}
