package com.anand.springproject.service.rabbitmq;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpListenerConfig {


    @Bean
    SimpleMessageListenerContainer container(@Qualifier("amqpConnectionFactory") ConnectionFactory connectionFactory,
                                             @Qualifier("demoQueue1ListenerAdapter") MessageListenerAdapter listenerAdapter) {

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();

        container.setConnectionFactory(connectionFactory);
        container.setQueueNames("demo.queue.1");
        container.setMessageListener(listenerAdapter);

        return container;
    }

    @Bean(name="demoQueue1ListenerAdapter")
    MessageListenerAdapter listenerAdapter(@Qualifier("demoQueue1MessageReceiver") DemoQueue1MessageReceiver receiver) {
        return new MessageListenerAdapter(receiver);
    }

}
