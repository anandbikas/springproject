package com.anand.springproject.rabbitmq;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class MyMessageListener implements MessageListener {

    private static final XLogger logger = XLoggerFactory.getXLogger(MyMessageListener.class);

    @Override
    public void onMessage(Message message) {

        logger.info("Received message: " + message);
        logger.info("Body: " + new String(message.getBody()));

    }
}
