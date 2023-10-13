package com.hbsites.rpgtracker.coc.service;

import com.hbsites.hbsitescommons.commons.interfaces.CRUDService;
import com.hbsites.rpgtracker.coc.dto.SessionDTO;
import com.hbsites.rpgtracker.coc.entity.SessionEntity;
import com.hbsites.rpgtracker.coc.producer.SessionBasicInfoRequestProducer;
import com.hbsites.rpgtracker.coc.repository.SessionRepository;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SessionService implements CRUDService<UUID, SessionDTO, SessionDTO, SessionDTO> {

    @Autowired
    @Lazy
    private SessionRepository repository;

    @Autowired
    @Lazy
    private SessionBasicInfoRequestProducer sessionBasicInfoRequestProducer;

    @Override
    public Page<SessionDTO> getAll(int page) {
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
    public SessionDTO create(SessionDTO coCSessionDTO) {
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
    public SessionDTO getById(UUID id) {
        return findEntityOrElseThrow(id).toDetailDTO(sessionBasicInfoRequestProducer);
    }

    @Override
    public SessionDTO update(UUID id, SessionDTO payload) {
//        CoCSessionEntity e = findEntityOrElseThrow(id);
//        // TODO Update basicInfo to Rabbit
//        e.setPulpCthulhu(payload.getPulpCthulhu());
//        e = repository.save(e);
//        return e.toDetailDTO();
        throw new NotImplementedException();
    }

    private SessionEntity findEntityOrElseThrow(UUID uuid) {
        return repository.findByCoreSessionId(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
