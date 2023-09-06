package com.hbsites.rpgtracker.coc.controller;

import com.hbsites.hbsitescommons.config.ApiVersion;
import com.hbsites.hbsitescommons.interfaces.CRUDRestController;
import com.hbsites.rpgtracker.coc.dto.CoCWeaponCreateDTO;
import com.hbsites.rpgtracker.coc.dto.CoCWeaponDetailDTO;
import com.hbsites.rpgtracker.coc.dto.CoCWeaponListDTO;
import com.hbsites.rpgtracker.coc.service.CoCWeaponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/weapons")
@ApiVersion(1)
public class CoCWeaponRestController implements CRUDRestController<CoCWeaponListDTO, CoCWeaponDetailDTO, UUID, CoCWeaponCreateDTO> {

    @Lazy
    @Autowired
    private CoCWeaponService service;

    @Override
    public List<CoCWeaponListDTO> getAll() {
        return service.getAll();
    }

    @Override
    public CoCWeaponDetailDTO create(CoCWeaponCreateDTO dto) {
        return service.create(dto);
    }

    @Override
    public CoCWeaponDetailDTO getOne(UUID uuid) {
        return service.getById(uuid);
    }

    @Override
    public CoCWeaponDetailDTO update(UUID uuid, CoCWeaponCreateDTO dto) {
        return service.update(uuid, dto);
    }

    @Override
    public void delete(UUID uuid) {
        service.deleteById(uuid);
    }
}
