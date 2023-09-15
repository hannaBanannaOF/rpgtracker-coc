package com.hbsites.rpgtracker.coc.entity;

import com.hbsites.hbsitescommons.entity.BaseEntity;
import com.hbsites.rpgtracker.coc.dto.SpellDetailDTO;
import com.hbsites.rpgtracker.coc.dto.SpellListingDTO;
import com.hbsites.rpgtracker.coc.enumeration.ESpellCategory;
import com.hbsites.rpgtracker.core.entity.CharacterSheetEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "spells")
public class SpellEntity extends BaseEntity<SpellListingDTO, SpellDetailDTO> {

    @Column(name = "id", columnDefinition = "uuid")
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name", nullable = false, columnDefinition = "varchar(50)")
    private String name;

    @Column(name = "monster_knowledge", columnDefinition = "varchar(50)")
    private String monsterKnowledge;

    @Column(name = "cost", nullable = false, columnDefinition = "varchar(200)")
    private String cost;

    @Column(name = "conjuring_time", nullable = false, columnDefinition = "varchar(50)")
    private String conjuringTime;

    @Column(name = "description", nullable = false, columnDefinition = "text")
    private String description;

    @Column(name = "visceral_form", columnDefinition = "text")
    private String visceralForm;

    @Column(name = "alternative_names", columnDefinition = "varchar(200)")
    private String alternativeNames;

    @OneToMany(mappedBy = "spell", fetch = FetchType.LAZY, targetEntity = SpellCategoryEntity.class)
    private List<SpellCategoryEntity> categories;

    public boolean isOnlyOniricLandscape() {
        return this.categories.stream().anyMatch(e -> ESpellCategory.ONIRIC_LANDSCAPE.equals(e.getCategory()));
    }

    public boolean isFolk() {
        return this.categories.stream().anyMatch(e -> ESpellCategory.FOLK.equals(e.getCategory()));
    }

    @Override
    public SpellListingDTO toListDTO() {
        return new SpellListingDTO(this.getId(), this.getName(), this.isOnlyOniricLandscape(),
                this.isFolk(), this.getMonsterKnowledge(), this.getAlternativeNames());
    }

    @Override
    public SpellDetailDTO toDetailDTO() {
        return new SpellDetailDTO(this.getId(), this.getName(),
                this.getMonsterKnowledge(), this.getCost(), this.getConjuringTime(),
                this.getDescription(), this.getVisceralForm(), this.getAlternativeNames(),
                this.getCategories().stream().map(e -> new SpellDetailDTO.CategoryDTO(e.getId(), e.getCategory())).collect(Collectors.toList()));
    }
}
