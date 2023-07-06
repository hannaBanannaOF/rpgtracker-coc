package com.hbsites.rpgtracker.coc.dto;

import com.hbsites.rpgtracker.coc.enumeration.ESkillKind;
import com.hbsites.rpgtracker.coc.enumeration.ESkillRarity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CoCSkillDetailDTO {
    private UUID id;
    private ESkillRarity rarity;
    private ESkillKind kind;
    private Boolean usable;
    private Integer baseValue;
    private String name;
    private CoCSkillDetailDTO parentSkill;
}
