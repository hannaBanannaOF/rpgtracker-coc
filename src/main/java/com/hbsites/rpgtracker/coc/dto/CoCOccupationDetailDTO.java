package com.hbsites.rpgtracker.coc.dto;

import com.hbsites.rpgtracker.coc.enumeration.ESkillKind;
import com.hbsites.rpgtracker.coc.enumeration.ESkillPointCalculationRule;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CoCOccupationDetailDTO {
    private UUID id;
    private String name;
    private String description;
    private ESkillPointCalculationRule skillPointCalculationRule;
    private Integer minimumCreditRating;
    private Integer maximumCreditRating;
    private String suggestedContacts;
    private Integer epochPersonalSkillChoices;
    private Integer typedSkillChoices;
    private ESkillKind typedSkillChoicesKind;
    private List<CoCSkillDTO> skills;
}
