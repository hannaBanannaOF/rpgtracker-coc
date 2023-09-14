package com.hbsites.rpgtracker.coc.repository;

import com.hbsites.rpgtracker.coc.entity.AmmoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AmmoRepository extends JpaRepository<AmmoEntity, UUID> {

    public Page<AmmoEntity> findAllByIdOrIdNotIn(Pageable page, UUID id, List<UUID> idNotIn);

    Page<AmmoEntity> findAllByNameContainsIgnoreCase(String search, Pageable page);
}
