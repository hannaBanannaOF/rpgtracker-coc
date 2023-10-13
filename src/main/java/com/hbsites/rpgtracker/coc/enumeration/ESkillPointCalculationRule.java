package com.hbsites.rpgtracker.coc.enumeration;

import com.hbsites.hbsitescommons.commons.dto.LookupData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.Collectors;

public enum ESkillPointCalculationRule {

    EDU4("EDU x 4"),
    EDU2_INT2("EDU x 2 + INT x 2"),
    EDU2_APP2("EDU x 2 + APP x 2"),
    EDU2_STR2("EDU x 2 + STR x 2"),
    EDU2_DEX2("EDU x 2 + DEX x 2"),
    EDU2_STR_OR_DEX_2("EDU x 2 + (STR x 2 or DEX x 2)"),
    EDU2_APP_OR_DEX_2("EDU x 2 + (APP x 2 or DEX x 2)"),
    EDU2_STR_OR_DEX_OR_APP_2("EDU x 2 + (STR x 2 or DEX x 2 or APP x 2)");

    private final String desc;

    private ESkillPointCalculationRule(String desc) {
        this.desc = desc;
    }

    public static final List<ESkillPointCalculationRule> all = List.of(EDU4, EDU2_INT2, EDU2_APP2, EDU2_STR2, EDU2_DEX2, EDU2_STR_OR_DEX_2, EDU2_APP_OR_DEX_2, EDU2_STR_OR_DEX_OR_APP_2);

    @Override
    public String toString() {
        return this.desc;
    }

    public static Page<LookupData> toLookupData(String search) {
        List<ESkillPointCalculationRule> filtered = all;
        if (search != null && !search.isBlank()) {
            filtered = all.stream().filter(e -> e.desc.toUpperCase().contains(search.toUpperCase())).collect(Collectors.toList());
        }
        return new PageImpl<>(filtered.stream().map(e -> new LookupData(e.name(), e.toString())).collect(Collectors.toList()), PageRequest.of(0, 20), all.size());
    }
}
