package com.example.demo.controller;
import com.example.demo.service.DataService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
@RestController
@RequestMapping("/websocket")
public class DataController {

    @Autowired
  private DataService dataService;


    @GetMapping("/loans")
    public JsonNode getAllLoans() throws InterruptedException {
        return dataService.getRandomLoansData();
    }

    @GetMapping("/url")
    public String getRunningUrl (HttpServletRequest request) {
        String url = "https://" + request.getServerName() + ":" +
                request.getServerPort();
        return url;
    }

    @MessageMapping("/livedata")
    @SendTo("/topic/loans")

    public ArrayNode getAllLoan() throws InterruptedException {

        Thread.sleep(100); // simulated delay
        return  dataService.getRandomLoansData();
    }

}
