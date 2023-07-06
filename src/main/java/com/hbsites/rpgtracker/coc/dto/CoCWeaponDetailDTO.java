package com.hbsites.rpgtracker.coc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
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
    private CoCAmmoDTO ammo;
    private CoCSkillDetailDTO skillUsed;
}
