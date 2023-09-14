package com.hbsites.rpgtracker.coc.entity;

import com.hbsites.hbsitescommons.entity.BaseEntity;
import com.hbsites.rpgtracker.coc.dto.CharacterSheetSkillDTO;
import com.hbsites.rpgtracker.coc.dto.CharacterSheetSpellDTO;
import com.hbsites.rpgtracker.coc.entity.ids.SkillCharacterSheetID;
import com.hbsites.rpgtracker.coc.entity.ids.SpellCharacterSheetID;
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
@Table(name = "spells_character_sheet")
@Entity
@IdClass(SpellCharacterSheetID.class)
public class CharacterSheetSpellEntity extends BaseEntity<CharacterSheetSpellDTO, CharacterSheetSpellDTO> {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_sheet_id", referencedColumnName = "id")
    private CharacterSheetEntity characterSheet;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spell_id", referencedColumnName = "id")
    private SpellEntity spell;

    @Column(name = "chosenName", columnDefinition = "varchar(50)")
    private String chosenName;

    @Override
    public CharacterSheetSpellDTO toListDTO() {
        return new CharacterSheetSpellDTO(this.spell.getId(),
                this.getChosenName() != null ? this.getChosenName() : this.spell.getName(),
                this.spell.getDescription(), this.spell.isOnlyOniricLandscape(),
                this.spell.isFolk(), this.spell.getMonsterKnowledge(), this.spell.getCost(),
                this.spell.getConjuringTime());
    }

    @Override
    public CharacterSheetSpellDTO toDetailDTO() {
        return new CharacterSheetSpellDTO(this.spell.getId(),
                this.getChosenName() != null ? this.getChosenName() : this.spell.getName(),
                this.spell.getDescription(), this.spell.isOnlyOniricLandscape(),
                this.spell.isFolk(), this.spell.getMonsterKnowledge(), this.spell.getCost(),
                this.spell.getConjuringTime());
    }
}
