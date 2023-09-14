package com.hbsites.rpgtracker.coc.entity;

import com.hbsites.hbsitescommons.entity.BaseEntity;
import com.hbsites.rpgtracker.coc.dto.PulpTalentDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "pulp_talents")
public class PulpTalentEntity extends BaseEntity<PulpTalentDTO, PulpTalentDTO> {
    @Column(name = "id", columnDefinition = "uuid")
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name", nullable = false, columnDefinition = "varchar(50)")
    private String name;

    @Column(name = "description", nullable = false, columnDefinition = "text")
    private String description;

    @Override
    public PulpTalentDTO toListDTO() {
        return new PulpTalentDTO(this.id, this.name, this.description);
    }

    @Override
    public PulpTalentDTO toDetailDTO() {
        return new PulpTalentDTO(this.id, this.name, this.description);
    }
}
