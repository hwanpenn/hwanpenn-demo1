package com.example.hwanpenndemo1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloHtmlController {
    @RequestMapping("/indexTest")
    public String indexTest(){
        return "html/test";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
