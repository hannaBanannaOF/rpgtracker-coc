package com.hbsites.rpgtracker.coc.dto;

import com.hbsites.hbsitescommons.dto.LookupData;
import com.hbsites.rpgtracker.coc.enumeration.ESkillKind;
import com.hbsites.rpgtracker.coc.enumeration.ESkillPointCalculationRule;
import com.hbsites.rpgtracker.coc.enumeration.ESkillRarity;
import com.hbsites.rpgtracker.coc.repository.CoCAmmoRepository;
import com.hbsites.rpgtracker.coc.repository.CoCSkillRepository;
import com.hbsites.rpgtracker.coc.service.CoCSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LookupService {

    @Autowired
    @Lazy
    private CoCSkillRepository skillRepository;

    @Autowired
    @Lazy
    private CoCAmmoRepository ammoRepository;

    public Page<LookupData> getLookupData(LookupClass lookupEntity, String initialValue) {
        return switch(lookupEntity) {
            case usableSkill -> getUsableSkills(initialValue);
            case notUsableSkill -> getNotUsableSkills(initialValue);
            case skillKind -> ESkillKind.toLookupData();
            case skillRarity -> ESkillRarity.toLookupData();
            case skillPointCalculationRule -> ESkillPointCalculationRule.toLookupData();
            case ammo -> getAmmo(initialValue);
            default -> Page.empty();
        };
    }

    public LookupData getLookupDataInitial(LookupClass lookupClass, String initialValue) {
        return switch(lookupClass) {
            case usableSkill, notUsableSkill -> skillRepository.findById(UUID.fromString(initialValue)).map(e -> new LookupData(e.getId().toString(), e.getName())).orElse(null);
            case skillKind -> ESkillKind.all.stream().filter(e -> e.equals(ESkillKind.valueOf(initialValue))).findFirst().map(e -> new LookupData(e.name(), e.toString())).orElse(null);
            case skillRarity -> ESkillRarity.all.stream().filter(e -> e.equals(ESkillRarity.valueOf(initialValue))).findFirst().map(e -> new LookupData(e.name(), e.toString())).orElse(null);
            case ammo -> ammoRepository.findById(UUID.fromString(initialValue)).map(e -> new LookupData(e.getId().toString(), e.getName())).orElse(null);
            case skillPointCalculationRule -> ESkillPointCalculationRule.all.stream().filter(e -> e.equals(ESkillPointCalculationRule.valueOf(initialValue))).findFirst().map(e -> new LookupData(e.name(), e.toString())).orElse(null);
        };
    }

    private Page<LookupData> getUsableSkills(String initialValue) {
        Pageable page = PageRequest.of(
                0, 20,  Sort.by("name")
        );
        if (initialValue != null && !initialValue.isBlank()) {
            return skillRepository.findAllByUsableTrueOrId(UUID.fromString(initialValue), page).map(e -> new LookupData(e.getId().toString(), e.getName()));
        } else {
            return skillRepository.findAllByUsableTrue(page).map(e -> new LookupData(e.getId().toString(), e.getName()));
        }
    }

    private Page<LookupData> getNotUsableSkills(String initialValue) {
        Pageable page = PageRequest.of(
                0, 20,  Sort.by("name")
        );
        if (initialValue != null && !initialValue.isBlank()) {
            return skillRepository.findAllByUsableFalseOrId(UUID.fromString(initialValue), page).map(e -> new LookupData(e.getId().toString(), e.getName()));
        } else {
            return skillRepository.findAllByUsableFalse(page).map(e -> new LookupData(e.getId().toString(), e.getName()));
        }
    }

    private Page<LookupData> getAmmo(String initialValue) {
        Pageable page = PageRequest.of(
                0, 20,  Sort.by("name")
        );

        if (initialValue != null && !initialValue.isBlank()) {
            UUID uuid = UUID.fromString(initialValue);
            return ammoRepository.findAllByIdOrIdNotIn(page, uuid, List.of(uuid)).map((item) -> new LookupData(item.getId().toString(), item.getName()));
        } else {
            return ammoRepository.findAll(page).map((item) -> new LookupData(item.getId().toString(), item.getName()));
        }
    }

    public enum LookupClass {
        notUsableSkill, skillRarity, skillKind, usableSkill, ammo, skillPointCalculationRule
    }
}
