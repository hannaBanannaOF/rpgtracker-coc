package com.hbsites.rpgtracker.coc.service;

import com.hbsites.hbsitescommons.commons.dto.LookupData;
import com.hbsites.rpgtracker.coc.entity.AmmoEntity;
import com.hbsites.rpgtracker.coc.entity.SkillEntity;
import com.hbsites.rpgtracker.coc.enumeration.ESkillKind;
import com.hbsites.rpgtracker.coc.enumeration.ESkillPointCalculationRule;
import com.hbsites.rpgtracker.coc.enumeration.ESkillRarity;
import com.hbsites.rpgtracker.coc.enumeration.ESpellCategory;
import com.hbsites.rpgtracker.coc.repository.AmmoRepository;
import com.hbsites.rpgtracker.coc.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LookupService {

    @Autowired
    @Lazy
    private SkillRepository skillRepository;

    @Autowired
    @Lazy
    private AmmoRepository ammoRepository;

    public Page<LookupData> getLookupData(LookupClass lookupEntity, String initialValue, String search) {
        return switch(lookupEntity) {
            case usableSkill -> getUsableSkills(initialValue, search);
            case notUsableSkill -> getNotUsableSkills(initialValue, search);
            case skillKind -> ESkillKind.toLookupData(search);
            case skillRarity -> ESkillRarity.toLookupData(search);
            case skillPointCalculationRule -> ESkillPointCalculationRule.toLookupData(search);
            case ammo -> getAmmo(initialValue, search);
            case spellCategory -> ESpellCategory.toLookupData(search);
            default -> Page.empty();
        };
    }

    private Page<LookupData> getUsableSkills(String initialValue, String search) {
        Pageable page = PageRequest.of(
                0, 20,  Sort.by("name")
        );

        Page<SkillEntity> data;
        if (search != null && !search.isBlank()) {
            data = skillRepository.findAllByUsableTrueAndNameContainsIgnoreCase(search, page);
        } else if (initialValue != null && !initialValue.isBlank()) {
            data = skillRepository.findAllByUsableTrueOrId(UUID.fromString(initialValue), page);
        } else {
            data = skillRepository.findAllByUsableTrue(page);
        }
        return data.map(e -> new LookupData(e.getId().toString(), e.getName()));
    }

    private Page<LookupData> getNotUsableSkills(String initialValue, String search) {
        Pageable page = PageRequest.of(
                0, 20,  Sort.by("name")
        );

        Page<SkillEntity> data;
        if (search != null && !search.isBlank()) {
            data = skillRepository.findAllByUsableFalseAndNameContainsIgnoreCase(search, page);
        } else if (initialValue != null && !initialValue.isBlank()) {
            data = skillRepository.findAllByUsableFalseOrId(UUID.fromString(initialValue), page);
        } else {
            data = skillRepository.findAllByUsableFalse(page);
        }

        return data.map(e -> new LookupData(e.getId().toString(), e.getName()));
    }

    private Page<LookupData> getAmmo(String initialValue, String search) {
        Pageable page = PageRequest.of(
                0, 20,  Sort.by("name")
        );

        Page<AmmoEntity> data;
        if (search != null && !search.isBlank()) {
            data = ammoRepository.findAllByNameContainsIgnoreCase(search, page);
        } else if (initialValue != null && !initialValue.isBlank()) {
            UUID uuid = UUID.fromString(initialValue);
            data = ammoRepository.findAllByIdOrIdNotIn(page, uuid, List.of(uuid));
        } else {
            data = ammoRepository.findAll(page);
        }
        return data.map((item) -> new LookupData(item.getId().toString(), item.getName()));
    }

    public enum LookupClass {
        notUsableSkill, skillRarity, skillKind, usableSkill, ammo, skillPointCalculationRule, spellCategory
    }
}
