package com.example.demo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.github.javafaker.Faker;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.data.mapping.AccessOptions;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

@Service

public class DataService{

    private static Log logger = LogFactory.getLog(DataService.class);

        public ArrayNode getRandomLoansData() throws InterruptedException {
            ObjectMapper mapper = new ObjectMapper();
            Faker faker = new Faker(new Locale("en-AU"));
            ArrayNode loan = mapper.createArrayNode();
            for (int i = 0; i < 20000; i++) {
                loan.add(mapper.createObjectNode()
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
            return loan;

    }


}

