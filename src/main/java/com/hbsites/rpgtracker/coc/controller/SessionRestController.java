package com.hbsites.rpgtracker.coc.controller;

import com.hbsites.hbsitescommons.commons.config.ApiVersion;
import com.hbsites.hbsitescommons.commons.interfaces.CRUDRestController;
import com.hbsites.rpgtracker.coc.dto.SessionDTO;
import com.hbsites.rpgtracker.coc.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/sessions")
@ApiVersion(1)
public class SessionRestController implements CRUDRestController<SessionDTO, SessionDTO, UUID, SessionDTO> {
    @Autowired
    @Lazy
    private SessionService sessionService;

    @Override
    public Page<SessionDTO> getAll(int page) {
        return sessionService.getAll(page);
    }

    @Override
    public SessionDTO create(SessionDTO payload) {
        return sessionService.create(payload);
    }

    @Override
    public SessionDTO getOne(UUID uuid) {
        return sessionService.getById(uuid);
    }

    @Override
    public SessionDTO update(UUID uuid, SessionDTO dto) {
        return sessionService.update(uuid, dto);
    }

    @Override
    public void delete(UUID uuid) {
        sessionService.deleteById(uuid);
    }
}
