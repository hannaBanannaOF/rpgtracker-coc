package com.hbsites.rpgtracker.coc.controller;

import com.hbsites.hbsitescommons.interfaces.CRUDRestController;
import com.hbsites.rpgtracker.coc.dto.CoCSkillCreateDTO;
import com.hbsites.rpgtracker.coc.dto.CoCSkillDTO;
import com.hbsites.rpgtracker.coc.dto.CoCSkillDetailDTO;
import com.hbsites.rpgtracker.coc.service.CoCSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/skills")
public class CoCSkillRestController implements CRUDRestController<CoCSkillDTO, CoCSkillDetailDTO, UUID, CoCSkillCreateDTO> {

    @Autowired
    @Lazy
    private CoCSkillService skillService;

    @Override
    public List<CoCSkillDTO> getAll() {
        return skillService.getAll();
    }

    @Override
    public CoCSkillDetailDTO create(CoCSkillCreateDTO payload) {
        return skillService.create(payload);
    }

    @Override
    public CoCSkillDetailDTO getOne(UUID uuid) {
        return skillService.getById(uuid);
    }

    @Override
    public CoCSkillDetailDTO update(UUID uuid, CoCSkillCreateDTO dto) {
        return skillService.update(uuid, dto);
    }

    @Override
    public void delete(UUID uuid) {
        skillService.deleteById(uuid);
    }
}
