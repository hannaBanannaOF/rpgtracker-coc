package com.hbsites.rpgtracker.coc.controller;

import com.hbsites.hbsitescommons.config.ApiVersion;
import com.hbsites.hbsitescommons.interfaces.CRUDRestController;
import com.hbsites.rpgtracker.coc.dto.CoCAmmoDTO;
import com.hbsites.rpgtracker.coc.service.CoCAmmoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ammo")
@ApiVersion(1)
public class CoCAmmoRestController implements CRUDRestController<CoCAmmoDTO, CoCAmmoDTO, UUID, CoCAmmoDTO> {

    @Lazy
    @Autowired
    private CoCAmmoService service;

    @Override
    public List<CoCAmmoDTO> getAll() {
        return service.getAll();
    }

    @Override
    public CoCAmmoDTO create(CoCAmmoDTO dto) {
        return service.create(dto);
    }

    @Override
    public CoCAmmoDTO getOne(UUID uuid) {
        return service.getById(uuid);
    }

    @Override
    public CoCAmmoDTO update(UUID uuid, CoCAmmoDTO dto) {
        return service.update(uuid, dto);
    }

    @Override
    public void delete(UUID uuid) {
        service.deleteById(uuid);
    }
}
