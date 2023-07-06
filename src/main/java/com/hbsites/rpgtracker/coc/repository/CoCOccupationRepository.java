package com.hbsites.rpgtracker.coc.repository;

import com.hbsites.rpgtracker.coc.entity.CoCOccupationEntity;
import com.hbsites.rpgtracker.coc.entity.CoCSkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CoCOccupationRepository extends JpaRepository<CoCOccupationEntity, UUID> {
    boolean existsBySkills(CoCSkillEntity e);
}
