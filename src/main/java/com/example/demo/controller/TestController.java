package com.example.demo.controller;

import com.example.demo.ws.WSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import schema.wsdl.LoginAgentResponse;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private WSClient wsClient;

    @RequestMapping(method = RequestMethod.GET)
    public void test() {
        LoginAgentResponse loginAgentResponse = wsClient.loginAgent("admin","123");
        int b =5;

    }


}
