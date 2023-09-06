package com.hbsites.rpgtracker.coc.listener;

import com.hbsites.hbsitescommons.messages.SessionBasicInfoDTOListPayload;
import com.hbsites.hbsitescommons.queues.RabbitQueues;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class SessionBasicInfoResponseListener {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @RabbitListener(queues = RabbitQueues.COC_SESSION_RESPONSE_QUEUE)
    public void process(SessionBasicInfoDTOListPayload message) {
        if (message.infos().isEmpty()) {
            return;
        }
        simpMessagingTemplate.convertAndSend("/topic/"+message.userRequested().toString()+"/sessions/"+message.session(), message);
    }

}
