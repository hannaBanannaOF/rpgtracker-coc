package com.hbsites.rpgtracker.coc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CharacterSheetWeaponDTO {
    private WeaponDetailDTO weapon;
    private Integer ammoLeft;
    private Integer roundsLeft;
    private Integer quantityCarry;
    private String nickname;
    private Integer totalAmmoLeft;
    private Integer successValue;
}
