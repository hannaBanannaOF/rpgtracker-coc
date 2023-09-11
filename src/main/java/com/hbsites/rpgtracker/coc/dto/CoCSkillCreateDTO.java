package com.hbsites.rpgtracker.coc.dto;

import com.hbsites.rpgtracker.coc.enumeration.ESkillKind;
import com.hbsites.rpgtracker.coc.enumeration.ESkillRarity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoCSkillCreateDTO {
    private String name;
    private Integer baseValue;
    private ESkillRarity rarity;
    private ESkillKind kind;
    private Boolean usable;
    private UUID parentSkillId;
}
