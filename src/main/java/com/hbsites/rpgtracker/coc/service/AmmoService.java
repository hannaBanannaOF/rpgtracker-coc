package com.hbsites.rpgtracker.coc.service;

import com.hbsites.hbsitescommons.interfaces.CRUDService;
import com.hbsites.rpgtracker.coc.dto.AmmoDTO;
import com.hbsites.rpgtracker.coc.entity.AmmoEntity;
import com.hbsites.rpgtracker.coc.repository.AmmoRepository;
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
public class AmmoService implements CRUDService<UUID, AmmoDTO, AmmoDTO, AmmoDTO> {

    @Lazy
    @Autowired
    private AmmoRepository repository;

    @Lazy
    @Autowired
    private WeaponRepository weaponRepository;

    @Override
    public Page<AmmoDTO> getAll(int page) {
        PageRequest pageRequest = PageRequest.of(
                page,
                20,
                Sort.by("name"));
        return repository.findAll(pageRequest).map(AmmoEntity::toListDTO);
    }

    @Override
    public AmmoDTO create(AmmoDTO dto) {
        AmmoEntity e = new AmmoEntity();
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
    public AmmoDTO getById(UUID id) {
        return findEntityOrElseThrow(id).toListDTO();
    }

    @Override
    public AmmoDTO update(UUID id, AmmoDTO payload) {
        AmmoEntity e = findEntityOrElseThrow(id);
        e.setName(payload.getName());
        Integer rounds = payload.getRoundsShotWithEach();
        e.setRoundsShotWithEach(rounds != null && rounds != 0 ? rounds : 1);
        e = repository.save(e);
        return e.toListDTO();
    }

    private AmmoEntity findEntityOrElseThrow(UUID uuid) {
        return repository.findById(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
