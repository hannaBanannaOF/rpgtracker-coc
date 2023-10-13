package com.hbsites.rpgtracker.coc.service;

import com.hbsites.hbsitescommons.commons.interfaces.CRUDService;
import com.hbsites.rpgtracker.coc.dto.PulpTalentDTO;
import com.hbsites.rpgtracker.coc.entity.PulpTalentEntity;
import com.hbsites.rpgtracker.coc.repository.PulpTalentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Component
public class PulpTalentService implements CRUDService<UUID, PulpTalentDTO, PulpTalentDTO, PulpTalentDTO> {

    @Autowired
    @Lazy
    private PulpTalentRepository repository;

    @Override
    public Page<PulpTalentDTO> getAll(int page) {
        PageRequest pageRequest = PageRequest.of(
                page,
                20,
                Sort.by("name"));
        return repository.findAll(pageRequest).map(PulpTalentEntity::toListDTO);
    }

    @Override
    public PulpTalentDTO create(PulpTalentDTO payload) {
        PulpTalentEntity newE = new PulpTalentEntity();
        newE.setName(payload.getName());
        newE.setDescription(payload.getDescription());
        newE = repository.save(newE);
        return newE.toDetailDTO();
    }

    @Override
    public void deleteById(UUID uuid) {
        repository.deleteById(uuid);
    }

    @Override
    public PulpTalentDTO getById(UUID uuid) {
        return findEntityOrElseThrow(uuid).toDetailDTO();
    }

    @Override
    public PulpTalentDTO update(UUID uuid, PulpTalentDTO dto) {
        PulpTalentEntity e = findEntityOrElseThrow(uuid);
        e.setDescription(dto.getDescription());
        e.setName(e.getName());
        e = repository.save(e);
        return e.toDetailDTO();
    }
    
    private PulpTalentEntity findEntityOrElseThrow(UUID uuid) {
        return repository.findById(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
