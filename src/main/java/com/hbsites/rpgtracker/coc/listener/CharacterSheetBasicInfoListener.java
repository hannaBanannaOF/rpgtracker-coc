package com.hbsites.rpgtracker.coc.listener;

import com.hbsites.hbsitescommons.commons.enumeration.EMicroservice;
import com.hbsites.hbsitescommons.rpgtracker.messages.CharacterSheetBasicInfoDTOListPayload;
import com.hbsites.hbsitescommons.commons.messages.UUIDListPayload;
import com.hbsites.hbsitescommons.commons.queues.RabbitQueues;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log4j2
public class CharacterSheetBasicInfoListener {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitQueues.COC_CHARACTER_SHEET_RESPONSE_QUEUE)
    public void process(CharacterSheetBasicInfoDTOListPayload message) {
        log.info("[CHARACTER-SHEET-RESPONSE] - Received message: %s".formatted(message.toString()));
        if (message.infos().isEmpty()) {
            return;
        }

        UUIDListPayload payload = new UUIDListPayload(List.of(message.infos().get(0).getPlayerId()), message.userRequested(), null, EMicroservice.RPGTRACKER_COC, message.infos().get(0).getSheetId());
        log.info("[CHARACTER-SHEET-RESPONSE] - Sending message to RabbitMQ (%s:%s): %s".formatted(RabbitQueues.USER_EXCHANGE, RabbitQueues.USER_REQUEST_QUEUE, payload.toString()));
        rabbitTemplate.convertAndSend(RabbitQueues.USER_EXCHANGE, RabbitQueues.USER_REQUEST_QUEUE, payload);

        String dest = "/topic/%s/character-sheet/%s/infos".formatted(message.userRequested().toString(), message.infos().get(0).getSheetId().toString());
        log.info("[CHARACTER-SHEET-RESPONSE] - Sending message to WS (%s): %s".formatted(dest, message.toString()));
        simpMessagingTemplate.convertAndSend(dest, message);
    }

}
