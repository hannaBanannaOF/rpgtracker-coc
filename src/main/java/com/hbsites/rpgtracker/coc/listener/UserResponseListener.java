package com.hbsites.rpgtracker.coc.listener;

import com.hbsites.hbsitescommons.commons.messages.UserDTOListPayload;
import com.hbsites.hbsitescommons.commons.queues.RabbitQueues;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class UserResponseListener {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @RabbitListener(queues = RabbitQueues.COC_USER_RESPONSE_QUEUE)
    public void process(UserDTOListPayload message) {
        log.info("[USER-RESPONSE] - Received message: %s".formatted(message.toString()));

        String dest = "/topic/%s/character-sheet/%s/infos".formatted(message.userRequested().toString(), message.characterSheet().toString());
        log.info("[USER-RESPONSE] - Sending message to WS (%s): %s".formatted(dest, message.toString()));
        simpMessagingTemplate.convertAndSend(dest, message);
    }
}
