package com.hbsites.rpgtracker.coc.controller;

import com.hbsites.hbsitescommons.config.ApiVersion;
import com.hbsites.rpgtracker.coc.dto.CharacterSheetDTO;
import com.hbsites.rpgtracker.coc.service.CharacterSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/character-sheets")
@ApiVersion(1)
public class CharacterSheetRestController {

    @Lazy
    @Autowired
    private CharacterSheetService service;

    @GetMapping("/{uuid}")
    public CharacterSheetDTO getOne(@PathVariable UUID uuid) {
        return service.getOne(uuid);
    }

}
