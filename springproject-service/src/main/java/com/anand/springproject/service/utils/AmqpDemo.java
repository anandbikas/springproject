/**
 * @author Bikas Anand
 */
package com.anand.springproject.service.utils;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AmqpDemo {

    private static final XLogger logger = XLoggerFactory.getXLogger(AmqpDemo.class);

    private static final String DEMO_FANOUT_EXCHANGE="demo.fanout.exchange";
    private static final String DEMO_DIRECT_EXCHANGE="demo.direct.exchange";
    private static final String DEMO_TOPIC_EXCHANGE="demo.topic.exchange";

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    @Qualifier("topicExchangeAmqpTemplate")
    private AmqpTemplate topicExchangeAmqpTemplate;

    /**
     *
     */
    public void test() {
        amqpTemplate.convertAndSend(DEMO_FANOUT_EXCHANGE, "xyz","Fanout message");

        amqpTemplate.convertAndSend(DEMO_DIRECT_EXCHANGE, "demo.queue.1","Direct message to key demo.queue.1");
        amqpTemplate.convertAndSend(DEMO_DIRECT_EXCHANGE, "demo.queue.2","Direct message to key demo.queue.2");

        amqpTemplate.convertAndSend(DEMO_TOPIC_EXCHANGE, "important.information","Topic message with key important.information");
        amqpTemplate.convertAndSend(DEMO_TOPIC_EXCHANGE, "spam.data","Topic message with key spam.data");

        topicExchangeAmqpTemplate.convertAndSend("important.information","Topic message with key important.information 1");

        logger.info("AmqpDemo testing done.");
    }

}
