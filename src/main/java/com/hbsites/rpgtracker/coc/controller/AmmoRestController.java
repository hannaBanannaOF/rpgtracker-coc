package com.hbsites.rpgtracker.coc.controller;

import com.hbsites.hbsitescommons.config.ApiVersion;
import com.hbsites.hbsitescommons.interfaces.CRUDRestController;
import com.hbsites.rpgtracker.coc.dto.AmmoDTO;
import com.hbsites.rpgtracker.coc.service.AmmoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/ammo")
@ApiVersion(1)
public class AmmoRestController implements CRUDRestController<AmmoDTO, AmmoDTO, UUID, AmmoDTO> {

    @Lazy
    @Autowired
    private AmmoService service;

    @Override
    public Page<AmmoDTO> getAll(int page) {
        return service.getAll(page);
    }

    @Override
    public AmmoDTO create(AmmoDTO dto) {
        return service.create(dto);
    }

    @Override
    public AmmoDTO getOne(UUID uuid) {
        return service.getById(uuid);
    }

    @Override
    public AmmoDTO update(UUID uuid, AmmoDTO dto) {
        return service.update(uuid, dto);
    }

    @Override
    public void delete(UUID uuid) {
        service.deleteById(uuid);
    }
}
