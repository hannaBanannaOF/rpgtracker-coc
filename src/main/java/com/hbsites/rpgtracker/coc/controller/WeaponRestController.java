package com.hbsites.rpgtracker.coc.controller;

import com.hbsites.hbsitescommons.config.ApiVersion;
import com.hbsites.hbsitescommons.interfaces.CRUDRestController;
import com.hbsites.rpgtracker.coc.dto.WeaponDetailDTO;
import com.hbsites.rpgtracker.coc.dto.WeaponListDTO;
import com.hbsites.rpgtracker.coc.service.WeaponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/weapons")
@ApiVersion(1)
public class WeaponRestController implements CRUDRestController<WeaponListDTO, WeaponDetailDTO, UUID, WeaponDetailDTO> {

    @Lazy
    @Autowired
    private WeaponService service;

    @Override
    public Page<WeaponListDTO> getAll(int page) {
        return service.getAll(page);
    }

    @Override
    public WeaponDetailDTO create(WeaponDetailDTO dto) {
        return service.create(dto);
    }

    @Override
    public WeaponDetailDTO getOne(UUID uuid) {
        return service.getById(uuid);
    }

    @Override
    public WeaponDetailDTO update(UUID uuid, WeaponDetailDTO dto) {
        return service.update(uuid, dto);
    }

    @Override
    public void delete(UUID uuid) {
        service.deleteById(uuid);
    }
}
