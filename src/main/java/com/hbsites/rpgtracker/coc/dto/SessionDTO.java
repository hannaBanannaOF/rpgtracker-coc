package com.hbsites.rpgtracker.coc.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hbsites.hbsitescommons.rpgtracker.dto.SessionListingDTO;
import com.hbsites.rpgtracker.coc.entity.SessionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"system"})
public class SessionDTO extends SessionListingDTO<SessionEntity> {
    private Boolean pulpCthulhu;
    private UUID coreId;

    @Override
    public SessionEntity toEntity() {
        SessionEntity e = new SessionEntity();
        e.setId(this.getUuid());
        e.setPulpCthulhu(this.getPulpCthulhu() != null ? this.getPulpCthulhu() : false);
        return e;
    }
}
