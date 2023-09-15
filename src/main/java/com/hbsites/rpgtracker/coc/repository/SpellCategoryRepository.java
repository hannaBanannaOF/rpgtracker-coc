package com.hbsites.rpgtracker.coc.repository;

import com.hbsites.rpgtracker.coc.entity.SpellCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpellCategoryRepository extends JpaRepository<SpellCategoryEntity, UUID> {
}
