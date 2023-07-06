package com.hbsites.rpgtracker.coc.repository;

import com.hbsites.rpgtracker.coc.entity.CoCPulpTalentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CoCPulpTalentRepository extends JpaRepository<CoCPulpTalentEntity, UUID> {
}
