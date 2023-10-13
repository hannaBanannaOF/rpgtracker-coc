package com.hbsites.rpgtracker.coc.enumeration;

import com.hbsites.hbsitescommons.commons.dto.LookupData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.Collectors;

public enum ESpellCategory {

    ENVIRONMENTAL("Ambiental"),
    BANISHMENT_CONTROL("Banimento / Controle"),
    COMBAT("Combate"),
    COMMUNICATION("Comunicação"),
    MONSTER_DEITY_SUMMON("Convocação de monstros / deuses"),
    MONSTER_CREATION("Criação de monstros"),
    ENCHANTMENT("Encantamento"),
    FOLK("Folclórico"),
    INFLUENCER("Influenciadores"),
    HARMFUL("Nocivo (para uso fora de combate)"),
    OTHER("Outros (utilitários)"),
    LIFE_PROLONGATION("Prolongação da vida"),
    PROTECTION("Proteção"),
    TEMPORAL("Temporais"),
    ONIRIC_LANDSCAPE("Terras Oníricas"),
    TRANSFORMATION("Transformação"),
    TRAVEL_TRANSPORTATION("Viagem e transporte");


    private final String desc;

    private ESpellCategory(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return this.desc;
    }

    public static final List<ESpellCategory> all = List.of(ENVIRONMENTAL, BANISHMENT_CONTROL, COMBAT,
            COMMUNICATION, MONSTER_DEITY_SUMMON, MONSTER_CREATION, ENCHANTMENT, FOLK, INFLUENCER,
            HARMFUL, OTHER, LIFE_PROLONGATION, TEMPORAL, ONIRIC_LANDSCAPE, TRANSFORMATION, TRAVEL_TRANSPORTATION);

    public static Page<LookupData> toLookupData(String search) {
        List<ESpellCategory> filtered = all;
        if (search != null && !search.isBlank()) {
            filtered = all.stream().filter(e -> e.desc.toUpperCase().contains(search.toUpperCase())).collect(Collectors.toList());
        }
        return new PageImpl<>(filtered.stream().map(e -> new LookupData(e.name(), e.toString())).collect(Collectors.toList()), PageRequest.of(0, 20), all.size());
    }
}
