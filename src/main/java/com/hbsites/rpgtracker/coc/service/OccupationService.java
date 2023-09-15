package com.hbsites.rpgtracker.coc.service;

import com.hbsites.hbsitescommons.dto.LookupData;
import com.hbsites.hbsitescommons.interfaces.CRUDService;
import com.hbsites.rpgtracker.coc.dto.OccupationDetailDTO;
import com.hbsites.rpgtracker.coc.dto.OccupationListingDTO;
import com.hbsites.rpgtracker.coc.entity.OccupationEntity;
import com.hbsites.rpgtracker.coc.entity.SkillEntity;
import com.hbsites.rpgtracker.coc.repository.OccupationRepository;
import com.hbsites.rpgtracker.coc.repository.SkillRepository;
import lombok.NonNull;
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
        OccupationEntity e = getOccupationEntity(new OccupationEntity(), dto);
        if (dto.getSkills() != null) {
            for (OccupationDetailDTO.OccupationSkills skill : dto.getSkills()) {
                SkillEntity skille = skillRepository.findById(skill.getSkillId()).orElse(null);
                if (skille != null) {
                    e.getSkills().add(skille);
                }
            }
        }
        e = repository.save(e);
        return e.toDetailDTO();
    }

    private static OccupationEntity getOccupationEntity(@NonNull OccupationEntity base, OccupationDetailDTO dto) {
        base.setName(dto.getName());
        base.setDescription(dto.getDescription());
        base.setEpochPersonalSkillChoices(dto.getEpochPersonalSkillChoices());
        base.setMinimumCreditRating(dto.getMinimumCreditRating());
        base.setMaximumCreditRating(dto.getMaximumCreditRating());
        base.setSuggestedContacts(dto.getSuggestedContacts());
        base.setTypedSkillChoices(dto.getTypedSkillChoices());
        base.setTypedSkillChoicesKind(dto.getTypedSkillChoicesKind());
        base.setSkillPointCalculationRule(dto.getSkillPointCalculationRule());
        return base;
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
        OccupationEntity e = getOccupationEntity(findEntityOrElseThrow(id), payload);
        if (e.getSkills() != null) {
            e.getSkills().clear();
        }
        if (payload.getSkills() != null) {
            for (OccupationDetailDTO.OccupationSkills skillid : payload.getSkills()) {
                SkillEntity skill = skillRepository.findById(skillid.getSkillId()).orElse(null);
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
