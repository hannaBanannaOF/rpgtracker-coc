package com.hbsites.rpgtracker.coc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CoCWeaponListDTO {
    private UUID id;
    private String name;
    private Boolean isMelee;
    private Boolean isThrowable;
    private Boolean isDualWield;
}
