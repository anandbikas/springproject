package com.anand.springproject.service.rabbitmq;

import com.anand.springproject.service.utils.AmqpDemo;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 *
 */
@Component
@Configuration
@ConditionalOnProperty(value="springproject.rabbit.enabled", havingValue = "true")
public class DemoQueue2MessageListener implements MessageListener {

    private static final XLogger logger = XLoggerFactory.getXLogger(DemoQueue2MessageListener.class);

    public static final String queueName = "demo.queue.2";

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void onMessage(Message message) {

        logger.info("Received message: " + message);
        logger.info("Body: " + new String(message.getBody()));


        LocalDateTime dateTime = LocalDateTime.now();
        amqpTemplate.convertAndSend(AmqpDemo.DEMO_DIRECT_EXCHANGE, "demo.queue.1","Direct message to key demo.queue.1 at: " + dateTime.toString());

    }


    @Bean(name="demoQueue2ListenerContainer")
    SimpleMessageListenerContainer container(@Qualifier("amqpConnectionFactory") ConnectionFactory connectionFactory,
                                             @Qualifier("demoQueue2MessageListener") MessageListener messageListener) {

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();

        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(messageListener);
        container.setConcurrentConsumers(2);

        return container;
    }

}
