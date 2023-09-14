package com.hbsites.rpgtracker.coc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CharacterSheetSpellDTO {
    private UUID spellID;
    private String spellChosenName;
    private String spellDescription;
    private Boolean onlyOniricLandscape;
    private Boolean folk;
    private String monsterKnowledge;
    private String cost;
    private String conjuringTime;
}
