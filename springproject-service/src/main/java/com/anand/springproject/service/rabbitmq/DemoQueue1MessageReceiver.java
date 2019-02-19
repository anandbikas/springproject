package com.anand.springproject.service.rabbitmq;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class DemoQueue1MessageReceiver {

    /**
     *
     * @param message
     */
    public void handleMessage(String message) {
        System.out.println("Message Received : " + message);
    }

}