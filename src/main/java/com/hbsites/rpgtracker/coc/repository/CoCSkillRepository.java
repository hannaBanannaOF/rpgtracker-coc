package com.hbsites.rpgtracker.coc.repository;

import com.hbsites.rpgtracker.coc.entity.CoCSkillEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CoCSkillRepository extends JpaRepository<CoCSkillEntity, UUID> {
    Page<CoCSkillEntity> findAllByUsableTrue(Pageable page);

    Page<CoCSkillEntity> findAllByUsableTrueOrId(UUID id, Pageable page);

    Page<CoCSkillEntity> findAllByUsableTrueAndNameContainsIgnoreCase(String name, Pageable page);

    Page<CoCSkillEntity> findAllByUsableFalse(Pageable page);

    Page<CoCSkillEntity> findAllByUsableFalseOrId(UUID id, Pageable page);

    Page<CoCSkillEntity> findAllByUsableFalseAndNameContainsIgnoreCase(String name, Pageable page);

    boolean existsByParentSkillId(UUID id);
}
