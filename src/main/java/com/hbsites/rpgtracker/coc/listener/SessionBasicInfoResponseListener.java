package com.hbsites.rpgtracker.coc.listener;

import com.hbsites.hbsitescommons.rpgtracker.messages.SessionBasicInfoDTOListPayload;
import com.hbsites.hbsitescommons.commons.queues.RabbitQueues;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class SessionBasicInfoResponseListener {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @RabbitListener(queues = RabbitQueues.COC_SESSION_RESPONSE_QUEUE)
    public void process(SessionBasicInfoDTOListPayload message) {
        log.info("[SESSION-RESPONSE] - Received message: %s".formatted(message.toString()));
        if (message.infos().isEmpty()) {
            return;
        }

        String dest = "/topic/%s/sessions/%s".formatted(message.userRequested().toString(), message.infos().get(0).getCoreId());
        log.info("[SESSION-RESPONSE] - Sending message to WS (%s): %s".formatted(dest, message.toString()));
        simpMessagingTemplate.convertAndSend(dest, message);
    }

}
