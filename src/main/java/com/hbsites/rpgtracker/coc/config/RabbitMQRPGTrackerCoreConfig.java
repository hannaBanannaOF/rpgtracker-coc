package com.hbsites.rpgtracker.coc.config;

import com.hbsites.hbsitescommons.queues.RabbitQueues;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({JacksonConfig.class})
public class RabbitMQRPGTrackerCoreConfig {

    @Autowired
    Jackson2JsonMessageConverter converter;

    @Bean
    Queue msgQueue() {

        return new Queue(RabbitQueues.RPGTRACKER_CORE_CHARACTER_SHEET_REQUEST);
    }

    @Bean
    Queue replyQueue() {

        return new Queue(RabbitQueues.RPGTRACKER_CORE_CHARACTER_SHEET_RESPONSE);
    }

    @Bean
    TopicExchange exchange() {

        return new TopicExchange(RabbitQueues.RPGTRACKER_CORE_EXCHANGE);
    }

    @Bean
    Binding msgBinding() {

        return BindingBuilder.bind(msgQueue()).to(exchange()).with(RabbitQueues.RPGTRACKER_CORE_CHARACTER_SHEET_REQUEST);
    }

    @Bean
    Binding replyBinding() {

        return BindingBuilder.bind(replyQueue()).to(exchange()).with(RabbitQueues.RPGTRACKER_CORE_CHARACTER_SHEET_RESPONSE);
    }

    @Bean(name = "rabbitCoreTemplate")
    RabbitTemplate rabbitCoreTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setReplyAddress(RabbitQueues.RPGTRACKER_CORE_CHARACTER_SHEET_RESPONSE);
        template.setMessageConverter(converter);
        template.setReplyTimeout(6000);
        return template;
    }

    @Bean
    SimpleMessageListenerContainer replyCoreContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(RabbitQueues.RPGTRACKER_CORE_CHARACTER_SHEET_RESPONSE);
        container.setMessageListener(rabbitCoreTemplate(connectionFactory));
        return container;
    }
}
