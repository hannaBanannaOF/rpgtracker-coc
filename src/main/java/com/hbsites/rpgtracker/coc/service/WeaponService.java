package com.hbsites.rpgtracker.coc.service;

import com.hbsites.hbsitescommons.commons.interfaces.CRUDService;
import com.hbsites.rpgtracker.coc.dto.WeaponDetailDTO;
import com.hbsites.rpgtracker.coc.dto.WeaponListDTO;
import com.hbsites.rpgtracker.coc.entity.WeaponEntity;
import com.hbsites.rpgtracker.coc.repository.AmmoRepository;
import com.hbsites.rpgtracker.coc.repository.SkillRepository;
import com.hbsites.rpgtracker.coc.repository.WeaponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class WeaponService implements CRUDService<UUID, WeaponListDTO, WeaponDetailDTO, WeaponDetailDTO> {

    @Lazy
    @Autowired
    private WeaponRepository repository;

    @Lazy
    @Autowired
    private AmmoRepository ammoRepository;

    @Lazy
    @Autowired
    private SkillRepository skillRepository;

    @Override
    public Page<WeaponListDTO> getAll(int page) {
        PageRequest pageRequest = PageRequest.of(
                page,
                20);
        return repository.findAll(pageRequest).map(WeaponEntity::toListDTO);
    }

    @Override
    public WeaponDetailDTO create(WeaponDetailDTO dto) {
        WeaponEntity e = new WeaponEntity();
        if (dto.getAmmoId() != null) {
            e.setAmmo(ammoRepository.findById(dto.getAmmoId()).orElse(null));
        }
        if (dto.getSkillUsedId() != null) {
            e.setSkillUsed(skillRepository.findById(dto.getSkillUsedId()).orElse(null));
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
    public WeaponDetailDTO getById(UUID id) {
        return findEntityOrElseThrow(id).toDetailDTO();
    }

    @Override
    public WeaponDetailDTO update(UUID id, WeaponDetailDTO payload) {
        WeaponEntity e = findEntityOrElseThrow(id);
        if (payload.getAmmoId() != null) {
            e.setAmmo(ammoRepository.findById(payload.getAmmoId()).orElse(null));
        }
        if (payload.getSkillUsedId() != null) {
            e.setSkillUsed(skillRepository.findById(payload.getSkillUsedId()).orElse(null));
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

    private WeaponEntity findEntityOrElseThrow(UUID uuid) {
        return repository.findById(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
