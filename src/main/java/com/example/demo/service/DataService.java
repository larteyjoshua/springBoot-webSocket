package com.example.demo.service;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.github.javafaker.Faker;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
@Service

public class DataService {


    ObjectMapper mapper = new ObjectMapper();
    public ArrayNode getRandomLoansData() throws InterruptedException {
        Faker faker = new Faker(new Locale("en-AU"));
        ArrayNode loans = mapper.createArrayNode();
        for (int i = 0; i < 20; i++) {
            loans.add(mapper.createObjectNode()
                    .put("riskDesk" , faker.commerce().department())
                    .put("asset" , faker.commerce().productName())
                    .put("account" , faker.letterify("07200??89"))
                    .put("cusip" , faker.letterify("999??89"))
                    .put("pv01" , faker.commerce().price())
                    .put("pv01Twos" , faker.commerce().price())
                    .put("pv01Tens" , faker.commerce().price())
                    .put("pv01Thirtys" , faker.commerce().price())
                    .put("dv01" , faker.commerce().price())
                    .put("convexity" , faker.commerce().price())
                    .put("kappa" , faker.commerce().price())
                    .put("purpose", faker.commerce().material()));
        }
        return loans;

    }



}
