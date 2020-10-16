package com.crisper.server.web.controller;

import com.crisper.server.web.models.Command;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/file/")
public class WebFileController {
    @GetMapping
    public void getFiles(@RequestBody Command command) throws Exception {

    }


}
