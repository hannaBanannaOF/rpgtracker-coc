package com.hbsites.rpgtracker.coc.repository;

import com.hbsites.rpgtracker.coc.entity.CoCCharacterSheetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CoCCharacterSheetRepository extends JpaRepository<CoCCharacterSheetEntity, UUID> {
    Optional<CoCCharacterSheetEntity> findByCoreCharacterSheetId(UUID coreCharacterSheetId);
}
