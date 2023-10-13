package com.hbsites.rpgtracker.coc.controller;

import com.hbsites.hbsitescommons.commons.config.ApiVersion;
import com.hbsites.hbsitescommons.commons.interfaces.CRUDRestController;
import com.hbsites.rpgtracker.coc.config.aop.EditableResource;
import com.hbsites.rpgtracker.coc.dto.PulpTalentDTO;
import com.hbsites.rpgtracker.coc.entity.OccupationEntity;
import com.hbsites.rpgtracker.coc.entity.PulpTalentEntity;
import com.hbsites.rpgtracker.coc.service.OccupationService;
import com.hbsites.rpgtracker.coc.service.PulpTalentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/pulp-talents")
@ApiVersion(1)
public class PulpTalentRestController implements CRUDRestController<PulpTalentDTO, PulpTalentDTO, UUID, PulpTalentDTO> {

    @Autowired
    @Lazy
    private PulpTalentService pulpTalentService;

    @Override
    public Page<PulpTalentDTO> getAll(int page) {
        return pulpTalentService.getAll(page);
    }

    @Override
    public PulpTalentDTO create(PulpTalentDTO payload) {
        return pulpTalentService.create(payload);
    }

    @Override
    public PulpTalentDTO getOne(UUID uuid) {
        return pulpTalentService.getById(uuid);
    }

    @Override
    @EditableResource(service = PulpTalentService.class, clazz = PulpTalentEntity.class)
    public PulpTalentDTO update(UUID uuid, PulpTalentDTO dto) {
        return pulpTalentService.update(uuid, dto);
    }

    @Override
    @EditableResource(service = PulpTalentService.class, clazz = PulpTalentEntity.class)
    public void delete(UUID uuid) {
        pulpTalentService.deleteById(uuid);
    }

}
