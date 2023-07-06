package com.hbsites.rpgtracker.coc.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hbsites.hbsitescommons.dto.SessionListingDTO;
import com.hbsites.hbsitescommons.dto.SessionSheetDTO;
import com.hbsites.rpgtracker.coc.entity.CoCSessionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"system"})
public class CoCSessionDTO extends SessionListingDTO<CoCSessionEntity> {
    private Boolean pulpCthulhu;
    private UUID coreId;
    private List<SessionSheetDTO> characterSheets;

    @Override
    public CoCSessionEntity toEntity() {
        CoCSessionEntity e = new CoCSessionEntity();
        e.setId(this.getUuid());
        e.setPulpCthulhu(this.getPulpCthulhu() != null ? this.getPulpCthulhu() : false);
        return e;
    }
}
