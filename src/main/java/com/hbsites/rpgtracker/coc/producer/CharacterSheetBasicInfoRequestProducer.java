package com.hbsites.rpgtracker.coc.producer;

import com.hbsites.hbsitescommons.enumeration.EMicroservice;
import com.hbsites.hbsitescommons.interfaces.EventProducerInterface;
import com.hbsites.hbsitescommons.messages.UUIDListPayload;
import com.hbsites.hbsitescommons.queues.RabbitQueues;
import com.hbsites.hbsitescommons.utils.UserUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class CharacterSheetBasicInfoRequestProducer implements EventProducerInterface {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void getFromRabbitMQ(List<UUID> uuids, UUID session, UUID characterSheet) {
        UUIDListPayload newMsg = new UUIDListPayload(uuids, UserUtils.getUserUUID(), session, EMicroservice.RPGTRACKER_COC, characterSheet);
        rabbitTemplate.convertAndSend(RabbitQueues.RPGTRACKER_CORE_EXCHANGE, RabbitQueues.CORE_CHARACTER_SHEET_REQUEST_QUEUE, newMsg);
    }

}
