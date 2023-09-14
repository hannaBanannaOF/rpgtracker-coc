package com.hbsites.rpgtracker.coc.service;

import com.hbsites.rpgtracker.coc.dto.CharacterSheetDTO;
import com.hbsites.rpgtracker.coc.dto.CharacterSheetSkillDTO;
import com.hbsites.rpgtracker.coc.dto.CharacterSheetWeaponDTO;
import com.hbsites.rpgtracker.coc.entity.CharacterSheetEntity;
import com.hbsites.rpgtracker.coc.entity.CharacterSheetSkillEntity;
import com.hbsites.rpgtracker.coc.entity.SkillEntity;
import com.hbsites.rpgtracker.coc.producer.CharacterSheetBasicInfoRequestProducer;
import com.hbsites.rpgtracker.coc.repository.CharacterSheetRepository;
import com.hbsites.rpgtracker.coc.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class CharacterSheetService {

    @Lazy
    @Autowired
    private CharacterSheetRepository repository;

    @Lazy
    @Autowired
    private SkillRepository skillRepository;

    @Lazy
    @Autowired
    private CharacterSheetBasicInfoRequestProducer characterSheetBasicInfoRequestProducer;

    public CharacterSheetDTO getOne(UUID coreId) {
        CharacterSheetEntity e = findOneOrElseThrow(coreId);
        CharacterSheetDTO dto = e.toDetailDTO(characterSheetBasicInfoRequestProducer);

        List<SkillEntity> usableSkills = skillRepository.findAllByUsableTrue(Pageable.unpaged()).stream().toList();

        // Popular lista de skills
        for (SkillEntity skill: usableSkills) {
            CharacterSheetSkillDTO skillDto = new CharacterSheetSkillDTO(skill.getId(), skill.getFullName(), skill.getAbsoluteValue(), false);
            CharacterSheetSkillEntity skillOnSheet = e.getSkills().stream().filter(skillSheet -> skillSheet.getSkill().getId().equals(skillDto.getSkillID())).reduce((a, b) -> {
                throw new IllegalStateException("Multiple elements!");
            }).orElse(null);
            if (skillOnSheet != null && skillOnSheet.getValue() != null) {
                skillDto.setValue(skillOnSheet.getValue());
                skillDto.setImprovementCheck(skillOnSheet.getImprovementCheck());
            }
            dto.getSkills().add(skillDto);
        }
        // Adicionar skills padrão da ficha
        dto.getSkills().add(new CharacterSheetSkillDTO(null, "Dodge", e.getDodge(), e.getDodgeImprovementCheck()));
        dto.getSkills().add(new CharacterSheetSkillDTO(null, "Language (Own)", e.getLanguageOwn(), e.getLanguageOwnImprovementCheck()));
        dto.getSkills().add(new CharacterSheetSkillDTO(null, "Cthulhu Mythos", e.getCthulhuMythos(), false));
        dto.getSkills().add(new CharacterSheetSkillDTO(null, "Credit Rating", e.getCreditRating(), false));

        dto.getSkills().sort(Comparator.comparing(CharacterSheetSkillDTO::getSkillName));

        // Calcular sucesso das armas
        for (CharacterSheetWeaponDTO weapon : dto.getWeapons()) {
            CharacterSheetSkillEntity used = e.getSkills().stream().filter((skill) -> skill.getSkill().getId().equals(weapon.getWeapon().getSkillUsedId())).findAny().orElse(null);
            if (used != null && used.getValue() != null) {
                weapon.setSuccessValue(used.getValue());
            }
        }
        return dto;
    }

    private CharacterSheetEntity findOneOrElseThrow(UUID id) {
        return repository.findByCoreCharacterSheetId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
