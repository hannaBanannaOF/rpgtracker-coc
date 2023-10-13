package com.hbsites.rpgtracker.coc.entity;

import com.hbsites.hbsitescommons.commons.dto.LookupData;
import com.hbsites.hbsitescommons.commons.entity.BaseEntity;
import com.hbsites.rpgtracker.coc.dto.OccupationDetailDTO;
import com.hbsites.rpgtracker.coc.dto.OccupationListingDTO;
import com.hbsites.rpgtracker.coc.enumeration.ESkillKind;
import com.hbsites.rpgtracker.coc.enumeration.ESkillPointCalculationRule;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Table(name = "occupation")
@Entity
public class OccupationEntity extends BaseEntity<OccupationListingDTO, OccupationDetailDTO> {

    @Column(name = "id", columnDefinition = "uuid")
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name", columnDefinition = "varchar(50)", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "text", nullable = false)
    private String description;

    @Column(name = "skill_points_calculation_rule", nullable = false, columnDefinition = "varchar(45)")
    @Enumerated(EnumType.STRING)
    private ESkillPointCalculationRule skillPointCalculationRule;

    @Column(name = "minimum_credit_rating", columnDefinition = "integer", nullable = false)
    private Integer minimumCreditRating;

    @Column(name = "maximum_credit_rating", columnDefinition = "integer", nullable = false)
    private Integer maximumCreditRating;

    @Column(name = "suggested_contacts", columnDefinition = "text")
    private String suggestedContacts;

    @Column(name = "epoch_personal_skill_choices", columnDefinition = "integer")
    private Integer epochPersonalSkillChoices;

    @Column(name = "typed_skill_choices", columnDefinition = "integer")
    private Integer typedSkillChoices;

    @Column(name = "typed_skill_choices_kind", columnDefinition = "varchar(45)")
    @Enumerated(EnumType.STRING)
    private ESkillKind typedSkillChoicesKind;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "occupation_skills",
            joinColumns = @JoinColumn(name = "occupation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "id"))
    private List<SkillEntity> skills = new ArrayList<>();

    @Column(name = "creator_id", columnDefinition = "uuid")
    private UUID creatorId;

    @Override
    public OccupationListingDTO toListDTO() {
        return new OccupationListingDTO(this.getId(), this.getName(), this.getDescription());
    }

    @Override
    public OccupationDetailDTO toDetailDTO() {
        OccupationDetailDTO dto = new OccupationDetailDTO(this.id, this.name, this.description,
                this.skillPointCalculationRule, this.minimumCreditRating, this.maximumCreditRating, this.suggestedContacts,
                this.epochPersonalSkillChoices, this.typedSkillChoices, this.typedSkillChoicesKind,
                this.getSkills().stream().map(e -> new OccupationDetailDTO.OccupationSkills(e.getId())).collect(Collectors.toList())
        );
        dto.setCreatorId(this.getCreatorId());
        return dto;
    }
}
