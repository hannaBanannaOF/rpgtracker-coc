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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({JacksonConfig.class})
public class RabbitMQRPGTrackerCoreSessionConfig {

    @Autowired
    Jackson2JsonMessageConverter converter;

    @Bean(name = "coreSessionRequestQueue")
    Queue msgQueue() {

        return new Queue(RabbitQueues.RPGTRACKER_CORE_SESSION_REQUEST);
    }

    @Bean(name = "coreSessionResponseQueue")
    Queue replyQueue() {

        return new Queue(RabbitQueues.RPGTRACKER_CORE_SESSION_RESPONSE);
    }

    @Bean(name = "coreSessionExchange")
    TopicExchange exchange() {

        return new TopicExchange(RabbitQueues.RPGTRACKER_CORE_EXCHANGE);
    }

    @Bean(name = "coreSessionRequestBinding")
    Binding msgBinding() {

        return BindingBuilder.bind(msgQueue()).to(exchange()).with(RabbitQueues.RPGTRACKER_CORE_SESSION_REQUEST);
    }

    @Bean(name = "coreSessionResponseBinding")
    Binding replyBinding() {

        return BindingBuilder.bind(replyQueue()).to(exchange()).with(RabbitQueues.RPGTRACKER_CORE_SESSION_RESPONSE);
    }

    @Bean(name = "rabbitCoreSessionTemplate")
    RabbitTemplate rabbitCoreTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setReplyAddress(RabbitQueues.RPGTRACKER_CORE_SESSION_RESPONSE);
        template.setMessageConverter(converter);
        template.setReplyTimeout(6000);
        return template;
    }

    @Bean(name = "coreSessionReplyContainer")
    SimpleMessageListenerContainer replyCoreContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(RabbitQueues.RPGTRACKER_CORE_SESSION_RESPONSE);
        container.setMessageListener(rabbitCoreTemplate(connectionFactory));
        return container;
    }
}
