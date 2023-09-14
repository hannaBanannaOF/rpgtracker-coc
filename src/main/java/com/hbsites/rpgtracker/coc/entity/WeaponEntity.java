package com.hbsites.rpgtracker.coc.entity;

import com.hbsites.hbsitescommons.entity.BaseEntity;
import com.hbsites.rpgtracker.coc.dto.WeaponDetailDTO;
import com.hbsites.rpgtracker.coc.dto.WeaponListDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "weapon")
public class WeaponEntity extends BaseEntity<WeaponListDTO, WeaponDetailDTO> {

    @Column(name = "id", columnDefinition = "uuid")
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name", nullable = false, columnDefinition = "varchar(50)")
    private String name;

    @Column(name = "range", columnDefinition = "varchar(20)")
    private String range;

    @Column(name = "damage", nullable = false, columnDefinition = "varchar(20)")
    private String damage;

    @Column(name = "attacks_per_round", nullable = false, columnDefinition = "integer")
    private Integer attacksPerRound = 1;

    @Column(name = "malfunction", columnDefinition = "integer")
    private Integer malfunction;

    @Column(name = "is_melee", nullable = false, columnDefinition = "boolean")
    private Boolean isMelee = false;

    @Column(name = "is_throwable", nullable = false, columnDefinition = "boolean")
    private Boolean isThrowable = false;

    @Column(name = "is_dual_wield", nullable = false, columnDefinition = "boolean")
    private Boolean isDualWield = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ammo_id")
    private AmmoEntity ammo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="skill_id")
    private SkillEntity skillUsed;

    @Override
    public WeaponListDTO toListDTO() {
        return new WeaponListDTO(this.id, this.name, this.isMelee, this.isThrowable, this.isDualWield);
    }

    @Override
    public WeaponDetailDTO toDetailDTO() {
        return new WeaponDetailDTO(this.id, this.name, this.range, this.damage, this.attacksPerRound, this.malfunction,
                this.isMelee, this.isThrowable, this.isDualWield, this.ammo != null ? this.ammo.getId() : null,
                this.skillUsed != null ? this.skillUsed.getId() : null);
    }
}
