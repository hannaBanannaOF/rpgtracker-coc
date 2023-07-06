package com.hbsites.rpgtracker.coc.producer;

import com.hbsites.hbsitescommons.dto.CharacterSheetBasicInfoDTO;
import com.hbsites.hbsitescommons.dto.SessionBasicInfoDTO;
import com.hbsites.hbsitescommons.interfaces.EventProducerInterface;
import com.hbsites.hbsitescommons.messages.SessionBasicInfoDTOListPayload;
import com.hbsites.hbsitescommons.messages.UUIDListPayload;
import com.hbsites.hbsitescommons.messages.UserDTOListPayload;
import com.hbsites.hbsitescommons.queues.RabbitQueues;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class SessionBasicInfoRequestProducer implements EventProducerInterface<List<SessionBasicInfoDTO>> {


    @Autowired
    @Qualifier("rabbitCoreSessionTemplate")
    RabbitTemplate rabbitCoreSessionTemplate;

    @Override
    public List<SessionBasicInfoDTO> getFromRabbitMQ(List<UUID> uuids) {
        UUIDListPayload newMsg = new UUIDListPayload(uuids);
        SessionBasicInfoDTOListPayload result = rabbitCoreSessionTemplate.convertSendAndReceiveAsType(RabbitQueues.RPGTRACKER_CORE_EXCHANGE, RabbitQueues.RPGTRACKER_CORE_SESSION_REQUEST, newMsg, new ParameterizedTypeReference<>(){});
        if (result != null) {
            return result.infos();
        }
        return new ArrayList<>();
    }
}
