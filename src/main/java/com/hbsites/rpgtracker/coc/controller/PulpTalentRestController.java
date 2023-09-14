package com.hbsites.rpgtracker.coc.controller;

import com.hbsites.hbsitescommons.config.ApiVersion;
import com.hbsites.hbsitescommons.interfaces.CRUDRestController;
import com.hbsites.rpgtracker.coc.dto.PulpTalentDTO;
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
    public PulpTalentDTO update(UUID uuid, PulpTalentDTO dto) {
        return pulpTalentService.update(uuid, dto);
    }

    @Override
    public void delete(UUID uuid) {
        pulpTalentService.deleteById(uuid);
    }

}
