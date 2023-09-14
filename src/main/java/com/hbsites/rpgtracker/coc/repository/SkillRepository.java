package com.hbsites.rpgtracker.coc.repository;

import com.hbsites.rpgtracker.coc.entity.SkillEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SkillRepository extends JpaRepository<SkillEntity, UUID> {
    Page<SkillEntity> findAllByUsableTrue(Pageable page);

    Page<SkillEntity> findAllByUsableTrueOrId(UUID id, Pageable page);

    Page<SkillEntity> findAllByUsableTrueAndNameContainsIgnoreCase(String name, Pageable page);

    Page<SkillEntity> findAllByUsableFalse(Pageable page);

    Page<SkillEntity> findAllByUsableFalseOrId(UUID id, Pageable page);

    Page<SkillEntity> findAllByUsableFalseAndNameContainsIgnoreCase(String name, Pageable page);

    boolean existsByParentSkillId(UUID id);
}
