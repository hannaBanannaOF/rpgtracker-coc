package com.hbsites.rpgtracker.coc.controller;

import com.hbsites.hbsitescommons.config.ApiVersion;
import com.hbsites.hbsitescommons.interfaces.CRUDRestController;
import com.hbsites.rpgtracker.coc.dto.SkillDTO;
import com.hbsites.rpgtracker.coc.dto.SkillDetailDTO;
import com.hbsites.rpgtracker.coc.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
@RestController
@RequestMapping("/skills")
@ApiVersion(1)
public class SkillRestController implements CRUDRestController<SkillDTO, SkillDetailDTO, UUID, SkillDetailDTO> {

    @Autowired
    @Lazy
    private SkillService skillService;

    @Override
    public Page<SkillDTO> getAll(int page) {
        return skillService.getAll(page);
    }

    @Override
    public SkillDetailDTO create(SkillDetailDTO payload) {
        return skillService.create(payload);
    }

    @Override
    public SkillDetailDTO getOne(UUID uuid) {
        return skillService.getById(uuid);
    }

    @Override
    public SkillDetailDTO update(UUID uuid, SkillDetailDTO dto) {
        return skillService.update(uuid, dto);
    }

    @Override
    public void delete(UUID uuid) {
        skillService.deleteById(uuid);
    }
}
