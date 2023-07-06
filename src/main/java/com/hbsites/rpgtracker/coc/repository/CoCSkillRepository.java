package com.hbsites.rpgtracker.coc.repository;

import com.hbsites.rpgtracker.coc.entity.CoCSkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CoCSkillRepository extends JpaRepository<CoCSkillEntity, UUID> {
    List<CoCSkillEntity> findAllByUsableTrue();

    boolean existsByParentSkillId(UUID id);
}
