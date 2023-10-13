package com.hbsites.rpgtracker.coc.entity;

import com.hbsites.hbsitescommons.commons.entity.BaseEntity;
import com.hbsites.rpgtracker.coc.dto.AmmoDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "ammo")
public class AmmoEntity extends BaseEntity<AmmoDTO, AmmoDTO> {

    @Column(name = "id", columnDefinition = "uuid")
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name", columnDefinition = "varcahr(50)", nullable = false)
    private String name;

    @Column(name = "rounds_shot_with_each", columnDefinition = "integer", nullable = false)
    private Integer roundsShotWithEach = 1;

    @Column(name = "creator_id", columnDefinition = "uuid")
    private UUID creatorId;

    @Override
    public AmmoDTO toListDTO() {
        AmmoDTO dto = new AmmoDTO(this.id, this.name, this.roundsShotWithEach);
        dto.setCreatorId(this.creatorId);
        return dto;
    }

    @Override
    public AmmoDTO toDetailDTO() {
        AmmoDTO dto = new AmmoDTO(this.id, this.name, this.roundsShotWithEach);
        dto.setCreatorId(this.creatorId);
        return dto;
    }
}
