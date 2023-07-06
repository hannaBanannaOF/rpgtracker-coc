package com.hbsites.rpgtracker.coc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CoCPulpTalentDTO {
    private UUID id;
    private String name;
    private String description;
}
