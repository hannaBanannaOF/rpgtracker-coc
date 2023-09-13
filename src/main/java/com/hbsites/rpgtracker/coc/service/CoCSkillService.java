package com.hbsites.rpgtracker.coc.service;

import com.hbsites.hbsitescommons.interfaces.CRUDService;
import com.hbsites.rpgtracker.coc.dto.CoCSkillDTO;
import com.hbsites.rpgtracker.coc.dto.CoCSkillDetailDTO;
import com.hbsites.rpgtracker.coc.entity.CoCSkillEntity;
import com.hbsites.rpgtracker.coc.repository.CoCOccupationRepository;
import com.hbsites.rpgtracker.coc.repository.CoCSkillRepository;
import com.hbsites.rpgtracker.coc.repository.CoCWeaponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class CoCSkillService implements CRUDService<UUID, CoCSkillDTO, CoCSkillDetailDTO, CoCSkillDetailDTO> {

    @Lazy
    @Autowired
    private CoCSkillRepository repository;

    @Lazy
    @Autowired
    private CoCOccupationRepository occupationRepository;

    @Lazy
    @Autowired
    private CoCWeaponRepository weaponRepository;

    @Override
    public Page<CoCSkillDTO> getAll(int page) {
        PageRequest pageRequest = PageRequest.of(
                page,
                20,
                Sort.by("name"));
        return repository.findAll(pageRequest).map(CoCSkillEntity::toListDTO);
    }

    @Override
    public CoCSkillDetailDTO create(CoCSkillDetailDTO coCSkillDTO) {
        CoCSkillEntity e = new CoCSkillEntity();
        e.setName(coCSkillDTO.getName());
        e.setKind(coCSkillDTO.getKind());
        e.setRarity(coCSkillDTO.getRarity());
        e.setUsable(coCSkillDTO.getUsable());
        e.setBaseValue(coCSkillDTO.getBaseValue());
        if (coCSkillDTO.getParentSkillId() != null) {
            e.setParentSkill(findEntityOrElseThrow(coCSkillDTO.getParentSkillId()));
        }
        e = repository.save(e);
        return e.toDetailDTO();
    }

    @Override
    public void deleteById(UUID id) {
        CoCSkillEntity e = findEntityOrElseThrow(id);
        if (repository.existsByParentSkillId(id) ||
                occupationRepository.existsBySkills(e) ||
                weaponRepository.existsBySkillUsedId(id)) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
        }
        repository.deleteById(id);
    }

    @Override
    public CoCSkillDetailDTO getById(UUID id) {
        return findEntityOrElseThrow(id).toDetailDTO();
    }

    @Override
    public CoCSkillDetailDTO update(UUID id, CoCSkillDetailDTO payload) {
        CoCSkillEntity e = findEntityOrElseThrow(id);
        e.setName(payload.getName());
        e.setRarity(payload.getRarity());
        e.setKind(payload.getKind());
        e.setUsable(payload.getUsable());
        e.setBaseValue(payload.getBaseValue());
        if (payload.getParentSkillId() != null) {
            e.setParentSkill(findEntityOrElseThrow(payload.getParentSkillId()));
        } else {
            e.setParentSkill(null);
        }
        e = repository.save(e);
        return e.toDetailDTO();
    }

    private CoCSkillEntity findEntityOrElseThrow(UUID uuid) {
        return repository.findById(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
