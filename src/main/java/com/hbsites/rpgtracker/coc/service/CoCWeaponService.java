package com.hbsites.rpgtracker.coc.service;

import com.hbsites.hbsitescommons.interfaces.CRUDService;
import com.hbsites.rpgtracker.coc.dto.CoCWeaponCreateDTO;
import com.hbsites.rpgtracker.coc.dto.CoCWeaponDetailDTO;
import com.hbsites.rpgtracker.coc.dto.CoCWeaponListDTO;
import com.hbsites.rpgtracker.coc.entity.CoCWeaponEntity;
import com.hbsites.rpgtracker.coc.repository.CoCAmmoRepository;
import com.hbsites.rpgtracker.coc.repository.CoCSkillRepository;
import com.hbsites.rpgtracker.coc.repository.CoCWeaponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CoCWeaponService implements CRUDService<UUID, CoCWeaponListDTO, CoCWeaponCreateDTO, CoCWeaponDetailDTO> {

    @Lazy
    @Autowired
    private CoCWeaponRepository repository;

    @Lazy
    @Autowired
    private CoCAmmoRepository ammoRepository;

    @Lazy
    @Autowired
    private CoCSkillRepository skillRepository;

    @Override
    public List<CoCWeaponListDTO> getAll() {
        return repository.findAll().stream().map(CoCWeaponEntity::toListDTO).collect(Collectors.toList());
    }

    @Override
    public CoCWeaponDetailDTO create(CoCWeaponCreateDTO dto) {
        CoCWeaponEntity e = new CoCWeaponEntity();
        if (dto.getAmmoID() != null) {
            e.setAmmo(ammoRepository.findById(dto.getAmmoID()).orElse(null));
        }
        if (dto.getSkillUsedID() != null) {
            e.setSkillUsed(skillRepository.findById(dto.getSkillUsedID()).orElse(null));
        }
        e.setDamage(dto.getDamage());
        e.setName(dto.getName());
        e.setRange(dto.getRange());
        e.setMalfunction(dto.getMalfunction());
        e.setAttacksPerRound(dto.getAttacksPerRound());
        e.setIsDualWield(dto.getIsDualWield());
        e.setIsMelee(dto.getIsMelee());
        e.setIsThrowable(dto.getIsThrowable());
        e = repository.save(e);
        return e.toDetailDTO();
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public CoCWeaponDetailDTO getById(UUID id) {
        return findEntityOrElseThrow(id).toDetailDTO();
    }

    @Override
    public CoCWeaponDetailDTO update(UUID id, CoCWeaponCreateDTO payload) {
        CoCWeaponEntity e = findEntityOrElseThrow(id);
        if (payload.getAmmoID() != null) {
            e.setAmmo(ammoRepository.findById(payload.getAmmoID()).orElse(null));
        }
        if (payload.getSkillUsedID() != null) {
            e.setSkillUsed(skillRepository.findById(payload.getSkillUsedID()).orElse(null));
        }
        e.setDamage(payload.getDamage());
        e.setName(payload.getName());
        e.setRange(payload.getRange());
        e.setMalfunction(payload.getMalfunction());
        e.setAttacksPerRound(payload.getAttacksPerRound());
        e.setIsDualWield(payload.getIsDualWield());
        e.setIsMelee(payload.getIsMelee());
        e.setIsThrowable(payload.getIsThrowable());
        e = repository.save(e);
        return e.toDetailDTO();
    }

    private CoCWeaponEntity findEntityOrElseThrow(UUID uuid) {
        return repository.findById(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
