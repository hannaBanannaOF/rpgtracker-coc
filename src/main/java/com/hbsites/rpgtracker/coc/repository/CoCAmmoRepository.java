package com.hbsites.rpgtracker.coc.repository;

import com.hbsites.rpgtracker.coc.entity.CoCAmmoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CoCAmmoRepository extends JpaRepository<CoCAmmoEntity, UUID> {

    public Page<CoCAmmoEntity> findAllByIdOrIdNotIn(Pageable page, UUID id, List<UUID> idNotIn);

    Page<CoCAmmoEntity> findAllByNameContainsIgnoreCase(String search, Pageable page);
}
