package com.hbsites.rpgtracker.coc.controller;

import com.hbsites.hbsitescommons.config.ApiVersion;
import com.hbsites.hbsitescommons.interfaces.CRUDRestController;
import com.hbsites.rpgtracker.coc.dto.CoCOccupationCreateDTO;
import com.hbsites.rpgtracker.coc.dto.CoCOccupationDetailDTO;
import com.hbsites.rpgtracker.coc.dto.CoCOccupationListingDTO;
import com.hbsites.rpgtracker.coc.service.CoCOccupationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/occupations")
@ApiVersion(1)
public class CoCOccupationRestController implements CRUDRestController<CoCOccupationListingDTO , CoCOccupationDetailDTO, UUID, CoCOccupationDetailDTO> {

    @Autowired
    @Lazy
    private CoCOccupationService occupationService;

    @Override
    public Page<CoCOccupationListingDTO> getAll(int page) {
        return occupationService.getAll(page);
    }

    @Override
    public CoCOccupationDetailDTO create(CoCOccupationDetailDTO payload) {
        return occupationService.create(payload);
    }

    @Override
    public CoCOccupationDetailDTO getOne(UUID uuid) {
        return occupationService.getById(uuid);
    }

    @Override
    public CoCOccupationDetailDTO update(UUID uuid, CoCOccupationDetailDTO dto) {
        return occupationService.update(uuid, dto);
    }

    @Override
    public void delete(UUID uuid) {
        occupationService.deleteById(uuid);
    }
}
