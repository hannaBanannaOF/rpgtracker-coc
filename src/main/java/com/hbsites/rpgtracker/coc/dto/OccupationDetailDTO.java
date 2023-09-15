package com.hbsites.rpgtracker.coc.dto;

import com.hbsites.hbsitescommons.dto.LookupData;
import com.hbsites.rpgtracker.coc.enumeration.ESkillKind;
import com.hbsites.rpgtracker.coc.enumeration.ESkillPointCalculationRule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OccupationDetailDTO {
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
    private List<OccupationSkills> skills;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OccupationSkills {
        private UUID skillId;
    }
}
