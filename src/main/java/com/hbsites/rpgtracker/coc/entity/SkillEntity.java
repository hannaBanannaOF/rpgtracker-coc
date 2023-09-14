package com.hbsites.rpgtracker.coc.entity;

import com.hbsites.hbsitescommons.entity.BaseEntity;
import com.hbsites.rpgtracker.coc.dto.SkillDTO;
import com.hbsites.rpgtracker.coc.dto.SkillDetailDTO;
import com.hbsites.rpgtracker.coc.enumeration.ESkillKind;
import com.hbsites.rpgtracker.coc.enumeration.ESkillRarity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "skills")
public class SkillEntity extends BaseEntity<SkillDTO, SkillDetailDTO> {

    @Column(name = "id", columnDefinition = "uuid")
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name", nullable = false, columnDefinition = "varchar(50)")
    private String name;

    @Column(name = "base_value", columnDefinition = "integer")
    private Integer baseValue;

    @Column(name = "skill_rarity", nullable = false, columnDefinition = "varchar(45)")
    @Enumerated(EnumType.STRING)
    private ESkillRarity rarity;

    @Column(name = "skill_kind", nullable = false, columnDefinition = "varchar(45)")
    @Enumerated(EnumType.STRING)
    private ESkillKind kind;

    @Column(name = "usable", columnDefinition = "boolean", nullable = false)
    private Boolean usable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_id")
    private SkillEntity parentSkill;

    @Transient
    private Integer absoluteValue;

    @Transient
    private String fullName;

    @PostLoad
    private void postLoad() {
        this.absoluteValue = (this.parentSkill != null && (this.baseValue == null || this.baseValue == 0)) ? this.parentSkill.getBaseValue() : this.baseValue;
        this.fullName = this.parentSkill != null ? this.name+" ("+ this.parentSkill.getName() +")" : this.name;
    }

    @Override
    public SkillDTO toListDTO() {
        return new SkillDTO(this.id, this.getFullName(), this.getAbsoluteValue());
    }

    @Override
    public SkillDetailDTO toDetailDTO() {
        return new SkillDetailDTO(this.id, this.rarity, this.kind, this.usable, this.baseValue, this.name, this.parentSkill != null ? this.parentSkill.getId() : null);
    }
}
