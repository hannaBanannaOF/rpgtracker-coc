package com.hbsites.rpgtracker.coc.entity;

import com.hbsites.hbsitescommons.entity.BaseEntity;
import com.hbsites.rpgtracker.coc.enumeration.ESpellCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import org.apache.commons.lang3.NotImplementedException;

import java.util.UUID;

@Data
@Entity
@Table(name = "spells_categories")
public class SpellCategoryEntity extends BaseEntity<Object, Object> {

    @Column(name = "id", columnDefinition = "uuid")
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="spell_id")
    private SpellEntity spell;

    @Column(name = "spell_category", nullable = false, columnDefinition = "varchar(45)")
    @Enumerated(EnumType.STRING)
    private ESpellCategory category;

    @Override
    public Object toListDTO() {
        throw new NotImplementedException();
    }

    @Override
    public Object toDetailDTO() {
        throw new NotImplementedException();
    }
}
