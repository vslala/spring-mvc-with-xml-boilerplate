package com.spring.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    @ResponseBody
    public String helloWorld() {
        return "Hello World!";
    }
}
