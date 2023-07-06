package com.hbsites.rpgtracker.coc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CoCCharacterSheetSkillDTO {
    private UUID skillID;
    private String skillName;
    private Integer value;
    private Boolean improvementCheck;
}
