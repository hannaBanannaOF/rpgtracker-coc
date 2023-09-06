package com.hbsites.rpgtracker.coc.service;

import com.hbsites.rpgtracker.coc.dto.CoCCharacterSheetDTO;
import com.hbsites.rpgtracker.coc.dto.CoCCharacterSheetSkillDTO;
import com.hbsites.rpgtracker.coc.entity.CoCCharacterSheetEntity;
import com.hbsites.rpgtracker.coc.entity.CoCCharacterSheetSkillEntity;
import com.hbsites.rpgtracker.coc.entity.CoCSkillEntity;
import com.hbsites.rpgtracker.coc.producer.CharacterSheetBasicInfoRequestProducer;
import com.hbsites.rpgtracker.coc.repository.CoCCharacterSheetRepository;
import com.hbsites.rpgtracker.coc.repository.CoCSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class CoCCharacterSheetService {

    @Lazy
    @Autowired
    private CoCCharacterSheetRepository repository;

    @Lazy
    @Autowired
    private CoCSkillRepository skillRepository;

    @Lazy
    @Autowired
    private CharacterSheetBasicInfoRequestProducer characterSheetBasicInfoRequestProducer;

    public CoCCharacterSheetDTO getOne(UUID coreId) {
        CoCCharacterSheetEntity e = findOneOrElseThrow(coreId);
        CoCCharacterSheetDTO dto = e.toDetailDTO(characterSheetBasicInfoRequestProducer);

        List<CoCSkillEntity> usableSkills = skillRepository.findAllByUsableTrue();

        // Popular lista de skills
        for (CoCSkillEntity skill: usableSkills) {
            CoCCharacterSheetSkillDTO skillDto = new CoCCharacterSheetSkillDTO(skill.getId(), skill.getFullName(), skill.getAbsoluteValue(), false);
            CoCCharacterSheetSkillEntity skillOnSheet = e.getSkills().stream().filter(skillSheet -> skillSheet.getSkill().getId().equals(skillDto.getSkillID())).reduce((a, b) -> {
                throw new IllegalStateException("Multiple elements!");
            }).orElse(null);
            if (skillOnSheet != null) {
                skillDto.setValue(skillOnSheet.getValue());
                skillDto.setImprovementCheck(skillOnSheet.getImprovementCheck());
            }
            dto.getSkills().add(skillDto);
        }
        // Adicionar skills padrão da ficha
        dto.getSkills().add(new CoCCharacterSheetSkillDTO(null, "Dodge", e.getDodge(), e.getDodgeImprovementCheck()));
        dto.getSkills().add(new CoCCharacterSheetSkillDTO(null, "Language (Own)", e.getLanguageOwn(), e.getLanguageOwnImprovementCheck()));
        dto.getSkills().add(new CoCCharacterSheetSkillDTO(null, "Cthulhu Mythos", e.getCthulhuMythos(), false));
        dto.getSkills().add(new CoCCharacterSheetSkillDTO(null, "Credit Rating", e.getCreditRating(), false));

        dto.getSkills().sort(Comparator.comparing(CoCCharacterSheetSkillDTO::getSkillName));

        return dto;
    }

    private CoCCharacterSheetEntity findOneOrElseThrow(UUID id) {
        return repository.findByCoreCharacterSheetId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
