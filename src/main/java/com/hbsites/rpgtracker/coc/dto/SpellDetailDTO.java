package com.hbsites.rpgtracker.coc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpellDetailDTO {
    private UUID id;
    private String name;
    private String monsterKnowledge;
    private String cost;
    private String conjuringTime;
    private String description;
    private String visceralForm;
    private String alternativeNames;
}
