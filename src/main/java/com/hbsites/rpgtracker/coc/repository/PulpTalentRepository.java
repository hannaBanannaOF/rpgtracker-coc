package com.hbsites.rpgtracker.coc.repository;

import com.hbsites.rpgtracker.coc.entity.PulpTalentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PulpTalentRepository extends JpaRepository<PulpTalentEntity, UUID> {
}
