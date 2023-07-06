package com.hbsites.rpgtracker.coc.repository;

import com.hbsites.rpgtracker.coc.entity.CoCCharacterSheetEntity;
import com.hbsites.rpgtracker.coc.entity.CoCSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CoCSessionRepository extends JpaRepository<CoCSessionEntity, UUID> {
    Optional<CoCSessionEntity> findByCoreSessionId(UUID coreSessionId);

}
