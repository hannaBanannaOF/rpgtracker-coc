package com.hbsites.rpgtracker.coc.controller;

import com.hbsites.hbsitescommons.commons.config.ApiVersion;
import com.hbsites.hbsitescommons.commons.dto.LookupData;
import com.hbsites.rpgtracker.coc.service.LookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lookup")
@ApiVersion(1)
public class LookupRestController {

    @Autowired
    private LookupService lookupService;

    @GetMapping
    public Page<LookupData> getLookupData(
            @RequestParam LookupService.LookupClass lookupClass,
            @RequestParam(required = false) String initialValue,
            @RequestParam(required = false) String search
    ) {
        return lookupService.getLookupData(lookupClass, initialValue, search);
    }
}
