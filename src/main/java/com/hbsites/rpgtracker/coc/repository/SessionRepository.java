package com.hbsites.rpgtracker.coc.repository;

import com.hbsites.rpgtracker.coc.entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, UUID> {
    Optional<SessionEntity> findByCoreSessionId(UUID coreSessionId);

}
