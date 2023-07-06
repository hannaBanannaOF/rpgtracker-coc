package com.hbsites.rpgtracker.coc.entity;

import com.hbsites.hbsitescommons.dto.SessionBasicInfoDTO;
import com.hbsites.hbsitescommons.dto.SessionListingDTO;
import com.hbsites.hbsitescommons.dto.SessionSheetDTO;
import com.hbsites.hbsitescommons.entity.BaseEntity;
import com.hbsites.hbsitescommons.entity.RabbitBaseEntity;
import com.hbsites.hbsitescommons.enumeration.ETRPGSystem;
import com.hbsites.hbsitescommons.interfaces.EventProducerInterface;
import com.hbsites.rpgtracker.coc.dto.CoCSessionDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import org.apache.commons.lang3.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "session")
public class CoCSessionEntity extends RabbitBaseEntity<CoCSessionDTO, CoCSessionDTO, List<SessionBasicInfoDTO>> {

    @Column(name = "id", columnDefinition = "uuid")
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "core_session_id", columnDefinition = "uuid")
    private UUID coreSessionId;

    @Column(name = "pulp_cthulhu", columnDefinition = "boolean")
    private Boolean pulpCthulhu;

    @Override
    public CoCSessionDTO toListDTO() {
        throw new NotImplementedException();
    }

    @Override
    public CoCSessionDTO toDetailDTO() {
        throw new NotImplementedException();
    }

    @Override
    public CoCSessionDTO toListDTO(EventProducerInterface<List<SessionBasicInfoDTO>> template) {
        CoCSessionDTO dto = new CoCSessionDTO(this.getPulpCthulhu(), this.getCoreSessionId(),null);
        SessionBasicInfoDTO info = template.getFromRabbitMQ(List.of(this.getCoreSessionId())).stream().findFirst().orElse(new SessionBasicInfoDTO());
        dto.populate(this.getId(), info.getSessionName(), ETRPGSystem.CALL_OF_CTHULHU, info.isInPlay(), new ArrayList<>());
        return dto;
    }

    @Override
    public CoCSessionDTO toDetailDTO(EventProducerInterface<List<SessionBasicInfoDTO>> template) {
        SessionBasicInfoDTO info = template.getFromRabbitMQ(List.of(this.getCoreSessionId())).stream().findFirst().orElse(new SessionBasicInfoDTO());
        CoCSessionDTO dto = new CoCSessionDTO(this.getPulpCthulhu(), this.getCoreSessionId(),info.getSessionSheets());
        dto.populate(this.getId(), info.getSessionName(), ETRPGSystem.CALL_OF_CTHULHU, info.isInPlay(), new ArrayList<>());
        return dto;
    }
}
