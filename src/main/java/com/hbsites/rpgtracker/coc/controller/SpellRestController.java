package com.hbsites.rpgtracker.coc.controller;

import com.hbsites.hbsitescommons.commons.config.ApiVersion;
import com.hbsites.hbsitescommons.commons.interfaces.CRUDRestController;
import com.hbsites.rpgtracker.coc.config.aop.EditableResource;
import com.hbsites.rpgtracker.coc.dto.SpellDetailDTO;
import com.hbsites.rpgtracker.coc.dto.SpellListingDTO;
import com.hbsites.rpgtracker.coc.entity.SkillEntity;
import com.hbsites.rpgtracker.coc.entity.SpellEntity;
import com.hbsites.rpgtracker.coc.service.SkillService;
import com.hbsites.rpgtracker.coc.service.SpellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/spells")
@ApiVersion(1)
public class SpellRestController implements CRUDRestController<SpellListingDTO, SpellDetailDTO, UUID, SpellDetailDTO> {

    @Lazy
    @Autowired
    private SpellService spellService;

    @Override
    public Page<SpellListingDTO> getAll(int page) {
        return spellService.getAll(page);
    }

    @Override
    public SpellDetailDTO create(SpellDetailDTO dto) {
        return spellService.create(dto);
    }

    @Override
    public SpellDetailDTO getOne(UUID uuid) {
        return spellService.getById(uuid);
    }

    @Override
    @EditableResource(service = SpellService.class, clazz = SpellEntity.class)
    public SpellDetailDTO update(UUID uuid, SpellDetailDTO dto) {
        return spellService.update(uuid, dto);
    }

    @Override
    @EditableResource(service = SpellService.class, clazz = SpellEntity.class)
    public void delete(UUID uuid) {
        spellService.deleteById(uuid);
    }
}
