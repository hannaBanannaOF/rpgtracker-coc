package com.hbsites.rpgtracker.coc.service;

import com.hbsites.hbsitescommons.interfaces.CRUDService;
import com.hbsites.hbsitescommons.utils.UserUtils;
import com.hbsites.rpgtracker.coc.dto.CoCSessionDTO;
import com.hbsites.rpgtracker.coc.entity.CoCSessionEntity;
import com.hbsites.rpgtracker.coc.producer.SessionBasicInfoRequestProducer;
import com.hbsites.rpgtracker.coc.repository.CoCSessionRepository;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CoCSessionService implements CRUDService<UUID, CoCSessionDTO, CoCSessionDTO, CoCSessionDTO> {

    @Autowired
    @Lazy
    private CoCSessionRepository repository;

    @Autowired
    @Lazy
    private SessionBasicInfoRequestProducer sessionBasicInfoRequestProducer;

    @Override
    public Page<CoCSessionDTO> getAll(int page) {
        PageRequest pageRequest = PageRequest.of(
                page,
                20);
        return new PageImpl<>(
                repository.findAll().stream().map(e -> e.toListDTO(sessionBasicInfoRequestProducer))
                        .collect(Collectors.toList()),
                pageRequest, 20
        );
    }

    @Override
    public CoCSessionDTO create(CoCSessionDTO coCSessionDTO) {
        throw new NotImplementedException();
//        CoCSessionEntity e = coCSessionDTO.toEntity();
//        //TODO mandar basicInfo pro RabbitMQ
//        e = repository.save(e);
//        return e.toDetailDTO();
    }

    @Override
    public void deleteById(UUID id) {
//        repository.deleteById(id);
        throw new NotImplementedException();
    }

    @Override
    public CoCSessionDTO getById(UUID id) {
        return findEntityOrElseThrow(id).toDetailDTO(sessionBasicInfoRequestProducer);
    }

    @Override
    public CoCSessionDTO update(UUID id, CoCSessionDTO payload) {
//        CoCSessionEntity e = findEntityOrElseThrow(id);
//        // TODO Update basicInfo to Rabbit
//        e.setPulpCthulhu(payload.getPulpCthulhu());
//        e = repository.save(e);
//        return e.toDetailDTO();
        throw new NotImplementedException();
    }

    private CoCSessionEntity findEntityOrElseThrow(UUID uuid) {
        return repository.findByCoreSessionId(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
