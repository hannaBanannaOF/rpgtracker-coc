package com.hbsites.rpgtracker.coc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CoCAmmoDTO {
    private UUID id;
    private String name;
    private Integer roundsShotWithEach;
}
