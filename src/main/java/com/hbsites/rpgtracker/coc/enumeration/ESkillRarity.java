package com.hbsites.rpgtracker.coc.enumeration;

import com.hbsites.hbsitescommons.dto.LookupData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.Collectors;

public enum ESkillRarity {
    COMMON("Comum"),
    ANTIQUE("1920 Era"),
    MODERN("Moderna"),
    PULP("Pulp Cthulhu");

    private final String desc;

    private ESkillRarity(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return this.desc;
    }

    public static final List<ESkillRarity> all = List.of(ANTIQUE, MODERN, COMMON, PULP);

    public static Page<LookupData> toLookupData(String search) {
        List<ESkillRarity> filtered = all;
        if (search != null && !search.isBlank()) {
            filtered = all.stream().filter(e -> e.desc.toUpperCase().contains(search.toUpperCase())).collect(Collectors.toList());
        }
        return new PageImpl<>(filtered.stream().map(e -> new LookupData(e.name(), e.toString())).collect(Collectors.toList()), PageRequest.of(0, 20), all.size());
    }
}
