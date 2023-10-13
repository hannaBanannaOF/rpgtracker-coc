package com.hbsites.rpgtracker.coc.controller;

import com.hbsites.hbsitescommons.commons.config.ApiVersion;
import com.hbsites.hbsitescommons.commons.interfaces.CRUDRestController;
import com.hbsites.rpgtracker.coc.config.aop.EditableResource;
import com.hbsites.rpgtracker.coc.dto.OccupationDetailDTO;
import com.hbsites.rpgtracker.coc.dto.OccupationListingDTO;
import com.hbsites.rpgtracker.coc.entity.AmmoEntity;
import com.hbsites.rpgtracker.coc.entity.OccupationEntity;
import com.hbsites.rpgtracker.coc.service.AmmoService;
import com.hbsites.rpgtracker.coc.service.OccupationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/occupations")
@ApiVersion(1)
public class OccupationRestController implements CRUDRestController<OccupationListingDTO, OccupationDetailDTO, UUID, OccupationDetailDTO> {

    @Autowired
    @Lazy
    private OccupationService occupationService;

    @Override
    public Page<OccupationListingDTO> getAll(int page) {
        return occupationService.getAll(page);
    }

    @Override
    public OccupationDetailDTO create(OccupationDetailDTO payload) {
        return occupationService.create(payload);
    }

    @Override
    public OccupationDetailDTO getOne(UUID uuid) {
        return occupationService.getById(uuid);
    }

    @Override
    @EditableResource(service = OccupationService.class, clazz = OccupationEntity.class)
    public OccupationDetailDTO update(UUID uuid, OccupationDetailDTO dto) {
        return occupationService.update(uuid, dto);
    }

    @Override
    @EditableResource(service = OccupationService.class, clazz = OccupationEntity.class)
    public void delete(UUID uuid) {
        occupationService.deleteById(uuid);
    }
}
