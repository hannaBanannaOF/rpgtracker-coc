package com.hbsites.rpgtracker.coc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoCWeaponDetailDTO {

    private UUID id;
    private String name;
    private String range;
    private String damage;
    private Integer attacksPerRound;
    private Integer malfunction;
    private Boolean isMelee;
    private Boolean isThrowable;
    private Boolean isDualWield;
    private UUID ammoId;
    private UUID skillUsedId;
}
