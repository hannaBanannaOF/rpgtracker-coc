package com.hbsites.rpgtracker.coc.controller;

import com.hbsites.hbsitescommons.config.ApiVersion;
import com.hbsites.hbsitescommons.interfaces.CRUDRestController;
import com.hbsites.rpgtracker.coc.dto.CoCSkillCreateDTO;
import com.hbsites.rpgtracker.coc.dto.CoCSkillDTO;
import com.hbsites.rpgtracker.coc.dto.CoCSkillDetailDTO;
import com.hbsites.rpgtracker.coc.service.CoCSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/skills")
@ApiVersion(1)
public class CoCSkillRestController implements CRUDRestController<CoCSkillDTO, CoCSkillDetailDTO, UUID, CoCSkillDetailDTO> {

    @Autowired
    @Lazy
    private CoCSkillService skillService;

    @Override
    public Page<CoCSkillDTO> getAll(int page) {
        return skillService.getAll(page);
    }

    @Override
    public CoCSkillDetailDTO create(CoCSkillDetailDTO payload) {
        return skillService.create(payload);
    }

    @Override
    public CoCSkillDetailDTO getOne(UUID uuid) {
        return skillService.getById(uuid);
    }

    @Override
    public CoCSkillDetailDTO update(UUID uuid, CoCSkillDetailDTO dto) {
        return skillService.update(uuid, dto);
    }

    @Override
    public void delete(UUID uuid) {
        skillService.deleteById(uuid);
    }
}
