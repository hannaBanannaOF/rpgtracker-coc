package com.hbsites.rpgtracker.coc.entity.ids;

import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.UUID;

@EqualsAndHashCode
public class WeaponCharacterSheetID implements Serializable {
    private UUID characterSheet;
    private UUID weapon;
}
