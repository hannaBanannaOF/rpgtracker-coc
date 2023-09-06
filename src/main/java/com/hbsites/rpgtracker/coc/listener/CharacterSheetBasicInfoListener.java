package com.hbsites.rpgtracker.coc.listener;

import com.hbsites.hbsitescommons.enumeration.EMicroservice;
import com.hbsites.hbsitescommons.messages.CharacterSheetBasicInfoDTOListPayload;
import com.hbsites.hbsitescommons.messages.UUIDListPayload;
import com.hbsites.hbsitescommons.queues.RabbitQueues;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CharacterSheetBasicInfoListener {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitQueues.COC_CHARACTER_SHEET_RESPONSE_QUEUE)
    public void process(CharacterSheetBasicInfoDTOListPayload message) {
        if (message.infos().isEmpty()) {
            return;
        }
        UUIDListPayload payload = new UUIDListPayload(List.of(message.infos().get(0).getPlayerId()), message.userRequested(), null, EMicroservice.RPGTRACKER_COC, message.infos().get(0).getSheetId());
        rabbitTemplate.convertAndSend(RabbitQueues.USER_EXCHANGE, RabbitQueues.USER_REQUEST_QUEUE, payload);
        simpMessagingTemplate.convertAndSend("/topic/"+ message.userRequested().toString() +"/character-sheet/"+message.infos().get(0).getSheetId().toString()+"/infos", message);
    }

}
