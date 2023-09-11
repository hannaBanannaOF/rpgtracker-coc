package com.hbsites.rpgtracker.coc.service;

import com.hbsites.hbsitescommons.interfaces.CRUDService;
import com.hbsites.rpgtracker.coc.dto.CoCAmmoDTO;
import com.hbsites.rpgtracker.coc.entity.CoCAmmoEntity;
import com.hbsites.rpgtracker.coc.repository.CoCAmmoRepository;
import com.hbsites.rpgtracker.coc.repository.CoCWeaponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CoCAmmoService implements CRUDService<UUID, CoCAmmoDTO, CoCAmmoDTO, CoCAmmoDTO> {

    @Lazy
    @Autowired
    private CoCAmmoRepository repository;

    @Lazy
    @Autowired
    private CoCWeaponRepository weaponRepository;

    @Override
    public Page<CoCAmmoDTO> getAll(int page) {
        PageRequest pageRequest = PageRequest.of(
                page,
                20,
                Sort.by("name"));
        return repository.findAll(pageRequest).map(CoCAmmoEntity::toListDTO);
    }

    @Override
    public CoCAmmoDTO create(CoCAmmoDTO dto) {
        CoCAmmoEntity e = new CoCAmmoEntity();
        e.setName(dto.getName());
        Integer rounds = dto.getRoundsShotWithEach();
        e.setRoundsShotWithEach(rounds != null && rounds != 0 ? rounds : 1);
        e = repository.save(e);
        return e.toListDTO();
    }

    @Override
    public void deleteById(UUID id) {
        if (weaponRepository.existsByAmmoId(id)) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
        }
        repository.deleteById(id);
    }

    @Override
    public CoCAmmoDTO getById(UUID id) {
        return findEntityOrElseThrow(id).toListDTO();
    }

    @Override
    public CoCAmmoDTO update(UUID id, CoCAmmoDTO payload) {
        CoCAmmoEntity e = findEntityOrElseThrow(id);
        e.setName(payload.getName());
        Integer rounds = payload.getRoundsShotWithEach();
        e.setRoundsShotWithEach(rounds != null && rounds != 0 ? rounds : 1);
        e = repository.save(e);
        return e.toListDTO();
    }

    private CoCAmmoEntity findEntityOrElseThrow(UUID uuid) {
        return repository.findById(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
