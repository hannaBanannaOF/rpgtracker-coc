package com.hbsites.rpgtracker.coc.controller;

import com.hbsites.hbsitescommons.commons.config.ApiVersion;
import com.hbsites.hbsitescommons.commons.interfaces.CRUDRestController;
import com.hbsites.rpgtracker.coc.config.aop.EditableResource;
import com.hbsites.rpgtracker.coc.dto.SkillDTO;
import com.hbsites.rpgtracker.coc.dto.SkillDetailDTO;
import com.hbsites.rpgtracker.coc.entity.PulpTalentEntity;
import com.hbsites.rpgtracker.coc.entity.SkillEntity;
import com.hbsites.rpgtracker.coc.service.PulpTalentService;
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
    @EditableResource(service = SkillService.class, clazz = SkillEntity.class)
    public SkillDetailDTO update(UUID uuid, SkillDetailDTO dto) {
        return skillService.update(uuid, dto);
    }

    @Override
    @EditableResource(service = SkillService.class, clazz = SkillEntity.class)
    public void delete(UUID uuid) {
        skillService.deleteById(uuid);
    }
}
