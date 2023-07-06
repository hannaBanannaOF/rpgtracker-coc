package com.hbsites.rpgtracker.coc.entity;

import com.hbsites.hbsitescommons.entity.BaseEntity;
import com.hbsites.rpgtracker.coc.dto.CoCCharacterSheetWeaponDTO;
import com.hbsites.rpgtracker.coc.entity.ids.CoCWeaponCharacterSheetID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "weapon_character_sheet")
@Entity
@IdClass(CoCWeaponCharacterSheetID.class)
public class CoCCharacterSheetWeaponEntity extends BaseEntity<CoCCharacterSheetWeaponDTO, CoCCharacterSheetWeaponDTO> {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_sheet_id", referencedColumnName = "id")
    private CoCCharacterSheetEntity characterSheet;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "weapon_id", referencedColumnName = "id")
    private CoCWeaponEntity weapon;

    @Column(name = "ammo_left", columnDefinition = "integer")
    private Integer ammoLeft;

    @Column(name = "rounds_left", columnDefinition = "integer")
    private Integer roundsLeft;

    @Column(name = "quantity_carry", columnDefinition = "integer")
    private Integer quantityCarry = 1;

    @Column(name = "nickname", columnDefinition = "varchar(50)")
    private String nickname;

    @Override
    public CoCCharacterSheetWeaponDTO toListDTO() {
        return new CoCCharacterSheetWeaponDTO(this.getWeapon().toDetailDTO(), this.getAmmoLeft(),
                this.getRoundsLeft(), this.getQuantityCarry(), this.getNickname(),
                this.getWeapon().getIsMelee() || this.getWeapon().getIsThrowable() ?
                        null :
                        this.getWeapon().getAmmo().getRoundsShotWithEach() * (this.getAmmoLeft() != null ? this.getAmmoLeft() : 0));
    }

    @Override
    public CoCCharacterSheetWeaponDTO toDetailDTO() {
        return new CoCCharacterSheetWeaponDTO(this.getWeapon().toDetailDTO(), this.getAmmoLeft(),
                this.getRoundsLeft(), this.getQuantityCarry(), this.getNickname(),
                this.getWeapon().getIsMelee() || this.getWeapon().getIsThrowable() ?
                        null :
                        this.getWeapon().getAmmo().getRoundsShotWithEach() * (this.getAmmoLeft() != null ? this.getAmmoLeft() : 0));
    }
}
