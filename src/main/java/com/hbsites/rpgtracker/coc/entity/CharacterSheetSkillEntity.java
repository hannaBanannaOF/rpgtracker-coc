package com.hbsites.rpgtracker.coc.entity;

import com.hbsites.hbsitescommons.entity.BaseEntity;
import com.hbsites.rpgtracker.coc.dto.CharacterSheetSkillDTO;
import com.hbsites.rpgtracker.coc.entity.ids.SkillCharacterSheetID;
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
@IdClass(SkillCharacterSheetID.class)
public class CharacterSheetSkillEntity extends BaseEntity<CharacterSheetSkillDTO, CharacterSheetSkillDTO> {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_sheet_id", referencedColumnName = "id")
    private CharacterSheetEntity characterSheet;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id", referencedColumnName = "id")
    private SkillEntity skill;

    @Column(name = "value", nullable = false, columnDefinition = "integer")
    private Integer value = 1;

    @Column(name = "improvement_check", nullable = false, columnDefinition = "boolean")
    private Boolean improvementCheck = false;

    @Override
    public CharacterSheetSkillDTO toListDTO() {
        return new CharacterSheetSkillDTO(this.skill.getId(), this.skill.getFullName(), this.value, this.improvementCheck);
    }

    @Override
    public CharacterSheetSkillDTO toDetailDTO() {
        return new CharacterSheetSkillDTO(this.skill.getId(), this.skill.getFullName(), this.value, this.improvementCheck);
    }
}
