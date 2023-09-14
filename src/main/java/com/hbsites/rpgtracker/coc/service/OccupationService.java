package com.hbsites.rpgtracker.coc.service;

import com.hbsites.hbsitescommons.interfaces.CRUDService;
import com.hbsites.rpgtracker.coc.dto.OccupationDetailDTO;
import com.hbsites.rpgtracker.coc.dto.OccupationListingDTO;
import com.hbsites.rpgtracker.coc.entity.OccupationEntity;
import com.hbsites.rpgtracker.coc.entity.SkillEntity;
import com.hbsites.rpgtracker.coc.repository.OccupationRepository;
import com.hbsites.rpgtracker.coc.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class OccupationService implements CRUDService<UUID, OccupationListingDTO, OccupationDetailDTO, OccupationDetailDTO> {
    @Autowired
    @Lazy
    private OccupationRepository repository;

    @Autowired
    @Lazy
    private SkillRepository skillRepository;

    @Override
    public Page<OccupationListingDTO> getAll(int page) {
        PageRequest pageRequest = PageRequest.of(
                page,
                20);
        return repository.findAll(pageRequest).map(OccupationEntity::toListDTO);
    }

    @Override
    public OccupationDetailDTO create(OccupationDetailDTO dto) {
        OccupationEntity e = new OccupationEntity();
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
                SkillEntity skill = skillRepository.findById(id).orElse(null);
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
    public OccupationDetailDTO getById(UUID id) {
        return findEntityOrElseThrow(id).toDetailDTO();
    }

    @Override
    public OccupationDetailDTO update(UUID id, OccupationDetailDTO payload) {
        OccupationEntity e = findEntityOrElseThrow(id);
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
        if (payload.getSkills() != null) {
            for (UUID skillid : payload.getSkills()) {
                SkillEntity skill = skillRepository.findById(skillid).orElse(null);
                if (skill != null) {
                    e.getSkills().add(skill);
                }
            }
        }
        e = repository.save(e);
        return e.toDetailDTO();
    }

    private OccupationEntity findEntityOrElseThrow(UUID uuid) {
        return repository.findById(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}