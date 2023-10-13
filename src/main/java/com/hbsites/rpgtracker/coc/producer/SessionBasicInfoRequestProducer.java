package com.hbsites.rpgtracker.coc.producer;

import com.hbsites.hbsitescommons.commons.enumeration.EMicroservice;
import com.hbsites.hbsitescommons.commons.interfaces.EventProducerInterface;
import com.hbsites.hbsitescommons.commons.messages.UUIDListPayload;
import com.hbsites.hbsitescommons.commons.queues.RabbitQueues;
import com.hbsites.hbsitescommons.commons.utils.UserUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Log4j2
public class SessionBasicInfoRequestProducer implements EventProducerInterface {


    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void getFromRabbitMQ(List<UUID> uuids, UUID session, UUID characterSheet) {
        UUIDListPayload newMsg = new UUIDListPayload(uuids, UserUtils.getUserUUID(), session, EMicroservice.RPGTRACKER_COC, characterSheet);

        log.info("[SESSION-REQUEST] - Sending message to RabbitMQ (%s:%s): %s".formatted(RabbitQueues.RPGTRACKER_CORE_EXCHANGE, RabbitQueues.CORE_SESSION_REQUEST_QUEUE, newMsg.toString()));
        rabbitTemplate.convertAndSend(RabbitQueues.RPGTRACKER_CORE_EXCHANGE, RabbitQueues.CORE_SESSION_REQUEST_QUEUE, newMsg);
    }
}
