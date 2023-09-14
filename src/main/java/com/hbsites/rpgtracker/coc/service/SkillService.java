package com.hbsites.rpgtracker.coc.service;

import com.hbsites.hbsitescommons.interfaces.CRUDService;
import com.hbsites.rpgtracker.coc.dto.SkillDTO;
import com.hbsites.rpgtracker.coc.dto.SkillDetailDTO;
import com.hbsites.rpgtracker.coc.entity.SkillEntity;
import com.hbsites.rpgtracker.coc.repository.OccupationRepository;
import com.hbsites.rpgtracker.coc.repository.SkillRepository;
import com.hbsites.rpgtracker.coc.repository.WeaponRepository;
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
public class SkillService implements CRUDService<UUID, SkillDTO, SkillDetailDTO, SkillDetailDTO> {

    @Lazy
    @Autowired
    private SkillRepository repository;

    @Lazy
    @Autowired
    private OccupationRepository occupationRepository;

    @Lazy
    @Autowired
    private WeaponRepository weaponRepository;

    @Override
    public Page<SkillDTO> getAll(int page) {
        PageRequest pageRequest = PageRequest.of(
                page,
                20,
                Sort.by("name"));
        return repository.findAll(pageRequest).map(SkillEntity::toListDTO);
    }

    @Override
    public SkillDetailDTO create(SkillDetailDTO coCSkillDTO) {
        SkillEntity e = new SkillEntity();
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
        SkillEntity e = findEntityOrElseThrow(id);
        if (repository.existsByParentSkillId(id) ||
                occupationRepository.existsBySkills(e) ||
                weaponRepository.existsBySkillUsedId(id)) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
        }
        repository.deleteById(id);
    }

    @Override
    public SkillDetailDTO getById(UUID id) {
        return findEntityOrElseThrow(id).toDetailDTO();
    }

    @Override
    public SkillDetailDTO update(UUID id, SkillDetailDTO payload) {
        SkillEntity e = findEntityOrElseThrow(id);
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

    private SkillEntity findEntityOrElseThrow(UUID uuid) {
        return repository.findById(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
