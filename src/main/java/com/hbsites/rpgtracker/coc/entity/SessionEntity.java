package com.hbsites.rpgtracker.coc.entity;

import com.hbsites.hbsitescommons.commons.entity.RabbitBaseEntity;
import com.hbsites.hbsitescommons.commons.interfaces.EventProducerInterface;
import com.hbsites.hbsitescommons.rpgtracker.enumeration.ETRPGSystem;
import com.hbsites.rpgtracker.coc.dto.SessionDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "session")
public class SessionEntity extends RabbitBaseEntity<SessionDTO, SessionDTO> {

    @Column(name = "id", columnDefinition = "uuid")
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "core_session_id", columnDefinition = "uuid")
    private UUID coreSessionId;

    @Column(name = "pulp_cthulhu", columnDefinition = "boolean")
    private Boolean pulpCthulhu;

    @Override
    public SessionDTO toListDTO(EventProducerInterface template) {
        template.getFromRabbitMQ(List.of(this.getCoreSessionId()), this.getId(), null);
        SessionDTO dto = new SessionDTO(this.getPulpCthulhu(), this.getCoreSessionId());
        dto.populate(this.getId(), null, ETRPGSystem.CALL_OF_CTHULHU, false, null);
        return dto;
    }

    @Override
    public SessionDTO toDetailDTO(EventProducerInterface template) {
        try {
            template.getFromRabbitMQ(List.of(this.getCoreSessionId()), this.getId(), null);
        } catch (Exception e) {
            // ignore it
        }
        SessionDTO dto = new SessionDTO(this.getPulpCthulhu(), this.getCoreSessionId());
        dto.populate(this.getId(), null, ETRPGSystem.CALL_OF_CTHULHU, false, null);
        return dto;
    }

    @Override
    public SessionDTO toListDTO() {
        throw new NotImplementedException();
    }

    @Override
    public SessionDTO toDetailDTO() {
        throw new NotImplementedException();
    }
}
