package com.hbsites.rpgtracker.coc.entity;

import com.hbsites.hbsitescommons.entity.BaseEntity;
import com.hbsites.hbsitescommons.interfaces.EventProducerInterface;
import com.hbsites.rpgtracker.coc.dto.CoCAmmoDTO;
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
public class CoCAmmoEntity extends BaseEntity<CoCAmmoDTO, CoCAmmoDTO> {

    @Column(name = "id", columnDefinition = "uuid")
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name", columnDefinition = "varcahr(50)", nullable = false)
    private String name;

    @Column(name = "rounds_shot_with_each", columnDefinition = "integer", nullable = false)
    private Integer roundsShotWithEach = 1;

    @Override
    public CoCAmmoDTO toListDTO() {
        return new CoCAmmoDTO(this.id, this.name, this.roundsShotWithEach);
    }

    @Override
    public CoCAmmoDTO toDetailDTO() {
        return new CoCAmmoDTO(this.id, this.name, this.roundsShotWithEach);
    }
}
