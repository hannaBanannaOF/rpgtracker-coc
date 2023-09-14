package com.hbsites.rpgtracker.coc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class SpellListingDTO {
    private UUID id;
    private String name;
    private Boolean onlyOniricLandscape;
    private Boolean folk;
    private String monsterKnowledge;
    private String alternativeNames;
}
