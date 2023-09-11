package com.hbsites.rpgtracker.coc.controller;

import com.hbsites.hbsitescommons.config.ApiVersion;
import com.hbsites.hbsitescommons.interfaces.CRUDRestController;
import com.hbsites.rpgtracker.coc.dto.CoCPulpTalentDTO;
import com.hbsites.rpgtracker.coc.service.CoCPulpTalentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pulp-talents")
@ApiVersion(1)
public class CoCPulpTalentRestController implements CRUDRestController<CoCPulpTalentDTO, CoCPulpTalentDTO, UUID, CoCPulpTalentDTO> {

    @Autowired
    @Lazy
    private CoCPulpTalentService pulpTalentService;

    @Override
    public Page<CoCPulpTalentDTO> getAll(int page) {
        return pulpTalentService.getAll(page);
    }

    @Override
    public CoCPulpTalentDTO create(CoCPulpTalentDTO payload) {
        return pulpTalentService.create(payload);
    }

    @Override
    public CoCPulpTalentDTO getOne(UUID uuid) {
        return pulpTalentService.getById(uuid);
    }

    @Override
    public CoCPulpTalentDTO update(UUID uuid, CoCPulpTalentDTO dto) {
        return pulpTalentService.update(uuid, dto);
    }

    @Override
    public void delete(UUID uuid) {
        pulpTalentService.deleteById(uuid);
    }

}
