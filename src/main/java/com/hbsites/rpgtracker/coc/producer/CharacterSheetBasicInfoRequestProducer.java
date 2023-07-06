package com.hbsites.rpgtracker.coc.producer;

import com.hbsites.hbsitescommons.dto.CharacterSheetBasicInfoDTO;
import com.hbsites.hbsitescommons.interfaces.EventProducerInterface;
import com.hbsites.hbsitescommons.messages.CharacterSheetBasicInfoDTOListPayload;
import com.hbsites.hbsitescommons.messages.UUIDListPayload;
import com.hbsites.hbsitescommons.queues.RabbitQueues;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Component
public class CharacterSheetBasicInfoRequestProducer implements EventProducerInterface<List<CharacterSheetBasicInfoDTO>> {

    @Autowired
    @Qualifier("rabbitCoreTemplate")
    private RabbitTemplate rabbitCoreTemplate;

    @Override
    public List<CharacterSheetBasicInfoDTO> getFromRabbitMQ(List<UUID> uuids) {

        UUIDListPayload newMsg = new UUIDListPayload(uuids);
        CharacterSheetBasicInfoDTOListPayload result = rabbitCoreTemplate.convertSendAndReceiveAsType(RabbitQueues.RPGTRACKER_CORE_EXCHANGE, RabbitQueues.RPGTRACKER_CORE_CHARACTER_SHEET_REQUEST, newMsg, new ParameterizedTypeReference<>(){});
        if (result != null) {
            return result.infos();
        }
        return new ArrayList<>();
    }

}
