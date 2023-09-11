package com.hbsites.rpgtracker.coc.enumeration;

import com.hbsites.hbsitescommons.dto.LookupData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.Collectors;

public enum ESkillKind {
    INTERPERSONAL("Interpessoal"),
    INVESTIGATION("Investigação"),
    COMBAT("Combate"),
    COMMON("Comum");

    private final String desc;

    private ESkillKind(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return this.desc;
    }

    public static final List<ESkillKind> all = List.of(INTERPERSONAL, COMBAT, COMMON, INVESTIGATION);

    public static Page<LookupData> toLookupData() {
        return new PageImpl<>(all.stream().map(e -> new LookupData(e.name(), e.toString())).collect(Collectors.toList()), PageRequest.of(0, 20), all.size());
    }
}
