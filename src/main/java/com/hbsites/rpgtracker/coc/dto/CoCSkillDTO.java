package com.hbsites.rpgtracker.coc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CoCSkillDTO {
    private UUID id;
    private String fullName;
    private Integer absoluteValue;
}
