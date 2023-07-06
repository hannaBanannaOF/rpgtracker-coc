package com.hbsites.rpgtracker.coc.entity;

import com.hbsites.hbsitescommons.entity.BaseEntity;
import com.hbsites.rpgtracker.coc.dto.CoCCharacterSheetSkillDTO;
import com.hbsites.rpgtracker.coc.entity.ids.CoCSkillCharacterSheetID;
import com.hbsites.rpgtracker.core.entity.CharacterSheetEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "skills_character_sheet")
@Entity
@IdClass(CoCSkillCharacterSheetID.class)
public class CoCCharacterSheetSkillEntity extends BaseEntity<CoCCharacterSheetSkillDTO, CoCCharacterSheetSkillDTO> {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_sheet_id", referencedColumnName = "id")
    private CoCCharacterSheetEntity characterSheet;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id", referencedColumnName = "id")
    private CoCSkillEntity skill;

    @Column(name = "value", nullable = false, columnDefinition = "integer")
    private Integer value = 1;

    @Column(name = "improvement_check", nullable = false, columnDefinition = "boolean")
    private Boolean improvementCheck = false;

    @Override
    public CoCCharacterSheetSkillDTO toListDTO() {
        return new CoCCharacterSheetSkillDTO(this.skill.getId(), this.skill.getFullName(), this.value, this.improvementCheck);
    }

    @Override
    public CoCCharacterSheetSkillDTO toDetailDTO() {
        return new CoCCharacterSheetSkillDTO(this.skill.getId(), this.skill.getFullName(), this.value, this.improvementCheck);
    }
}
