package com.example.lab4_2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RequestController {

    @GetMapping(value = "/")
    public String getMainPage() {
        return "index";
    }

}
