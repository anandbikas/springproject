package com.anand.springproject.service.rabbitmq;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
@ConditionalOnProperty(value="rabbit.enabled", havingValue = "true")
public class DemoQueue1MessageReceiver {

    public static final String queueName = "demo.queue.1";

    /**
     *
     * @param message
     */
    public void handleMessage(String message) {
        System.out.println("Message Received : " + message);
    }


    @Bean(name="demoQueue1ListenerContainer")
    SimpleMessageListenerContainer container(@Qualifier("amqpConnectionFactory") ConnectionFactory connectionFactory,
                                             @Qualifier("demoQueue1ListenerAdapter") MessageListenerAdapter listenerAdapter) {

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();

        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        container.setConcurrentConsumers(2);

        return container;
    }

    @Bean(name="demoQueue1ListenerAdapter")
    MessageListenerAdapter listenerAdapter(@Qualifier("demoQueue1MessageReceiver") DemoQueue1MessageReceiver receiver) {
        return new MessageListenerAdapter(receiver);
    }
}