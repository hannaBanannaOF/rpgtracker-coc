package com.hbsites.rpgtracker.coc.service;

import com.hbsites.hbsitescommons.interfaces.CRUDService;
import com.hbsites.rpgtracker.coc.dto.CoCOccupationCreateDTO;
import com.hbsites.rpgtracker.coc.dto.CoCOccupationDetailDTO;
import com.hbsites.rpgtracker.coc.dto.CoCOccupationListingDTO;
import com.hbsites.rpgtracker.coc.entity.CoCOccupationEntity;
import com.hbsites.rpgtracker.coc.entity.CoCSkillEntity;
import com.hbsites.rpgtracker.coc.repository.CoCOccupationRepository;
import com.hbsites.rpgtracker.coc.repository.CoCSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CoCOccupationService implements CRUDService<UUID, CoCOccupationListingDTO, CoCOccupationCreateDTO, CoCOccupationDetailDTO> {
    @Autowired
    @Lazy
    private CoCOccupationRepository repository;

    @Autowired
    @Lazy
    private CoCSkillRepository skillRepository;

    @Override
    public List<CoCOccupationListingDTO> getAll() {
        return repository.findAll().stream().map(CoCOccupationEntity::toListDTO).collect(Collectors.toList());
    }

    @Override
    public CoCOccupationDetailDTO create(CoCOccupationCreateDTO dto) {
        CoCOccupationEntity e = new CoCOccupationEntity();
        e.setName(dto.getName());
        e.setDescription(dto.getDescription());
        e.setEpochPersonalSkillChoices(dto.getEpochPersonalSkillChoices());
        e.setMinimumCreditRating(dto.getMinimumCreditRating());
        e.setMaximumCreditRating(dto.getMaximumCreditRating());
        e.setSuggestedContacts(dto.getSuggestedContacts());
        e.setTypedSkillChoices(dto.getTypedSkillChoices());
        e.setTypedSkillChoicesKind(dto.getTypedSkillChoicesKind());
        e.setSkillPointCalculationRule(dto.getSkillPointCalculationRule());
        if (dto.getSkills() != null) {
            for (UUID id : dto.getSkills()) {
                CoCSkillEntity skill = skillRepository.findById(id).orElse(null);
                if (skill != null) {
                    e.getSkills().add(skill);
                }
            }
        }
        e = repository.save(e);
        return e.toDetailDTO();
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public CoCOccupationDetailDTO getById(UUID id) {
        return findEntityOrElseThrow(id).toDetailDTO();
    }

    @Override
    public CoCOccupationDetailDTO update(UUID id, CoCOccupationCreateDTO payload) {
        CoCOccupationEntity e = findEntityOrElseThrow(id);
        e.setName(payload.getName());
        e.setDescription(payload.getDescription());
        e.setEpochPersonalSkillChoices(payload.getEpochPersonalSkillChoices());
        e.setMinimumCreditRating(payload.getMinimumCreditRating());
        e.setMaximumCreditRating(payload.getMaximumCreditRating());
        e.setSuggestedContacts(payload.getSuggestedContacts());
        e.setTypedSkillChoices(payload.getTypedSkillChoices());
        e.setTypedSkillChoicesKind(payload.getTypedSkillChoicesKind());
        e.setSkillPointCalculationRule(payload.getSkillPointCalculationRule());
        if (e.getSkills() != null) {
            e.getSkills().clear();
        }
        for (UUID skillid : payload.getSkills()) {
            CoCSkillEntity skill = skillRepository.findById(skillid).orElse(null);
            if (skill != null) {
                e.getSkills().add(skill);
            }
        }
        e = repository.save(e);
        return e.toDetailDTO();
    }

    private CoCOccupationEntity findEntityOrElseThrow(UUID uuid) {
        return repository.findById(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
