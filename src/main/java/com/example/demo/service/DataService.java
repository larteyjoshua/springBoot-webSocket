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

public class DataService implements ApplicationListener<BrokerAvailabilityEvent> {
    private SimpMessagingTemplate template;

    private static Log logger = LogFactory.getLog(DataService.class);

    private final MessageSendingOperations<String> messagingTemplate;

//    private final GeneratorData generatorData = new GeneratorData();

    private AtomicBoolean brokerAvailable = new AtomicBoolean();

    public DataService(MessageSendingOperations<String> messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void onApplicationEvent(BrokerAvailabilityEvent brokerAvailabilityEvent) {

    }

//    @Scheduled(fixedDelay=2000)
//    public void sendData() throws InterruptedException {
//        for (JsonNode data : this.generatorData.getRandomLoansData()) {
//            System.out.println("I am working here");
//            System.out.println(data);
//            this.messagingTemplate.convertAndSend("/topic/loans" +  data);
////            if (logger.isTraceEnabled()) {
////                logger.trace("Sending quote " + data);
////            }
//            System.out.println(this.brokerAvailable.get());
////            if (this.brokerAvailable.get()) {
////                this.messagingTemplate.convertAndSend("/topic/loans" +  data);
////            }
//        }
//    }


//
//    private static class GeneratorData {
        ObjectMapper mapper = new ObjectMapper();
        @Scheduled(fixedDelay=2000)
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

//        }

    }


}

