package com.hbsites.rpgtracker.coc.service;

import com.hbsites.hbsitescommons.interfaces.CRUDService;
import com.hbsites.rpgtracker.coc.dto.CoCPulpTalentDTO;
import com.hbsites.rpgtracker.coc.entity.CoCPulpTalentEntity;
import com.hbsites.rpgtracker.coc.repository.CoCPulpTalentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CoCPulpTalentService implements CRUDService<UUID, CoCPulpTalentDTO, CoCPulpTalentDTO, CoCPulpTalentDTO> {

    @Autowired
    @Lazy
    private CoCPulpTalentRepository repository;

    @Override
    public List<CoCPulpTalentDTO> getAll() {
        return repository.findAll().stream().map(CoCPulpTalentEntity::toListDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CoCPulpTalentDTO create(CoCPulpTalentDTO payload) {
        CoCPulpTalentEntity newE = new CoCPulpTalentEntity();
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
    public CoCPulpTalentDTO getById(UUID uuid) {
        return findEntityOrElseThrow(uuid).toDetailDTO();
    }

    @Override
    public CoCPulpTalentDTO update(UUID uuid, CoCPulpTalentDTO dto) {
        CoCPulpTalentEntity e = findEntityOrElseThrow(uuid);
        e.setDescription(dto.getDescription());
        e.setName(e.getName());
        e = repository.save(e);
        return e.toDetailDTO();
    }
    
    private CoCPulpTalentEntity findEntityOrElseThrow(UUID uuid) {
        return repository.findById(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
