package com.hbsites.rpgtracker.coc.repository;
import com.hbsites.rpgtracker.coc.entity.CoCWeaponEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CoCWeaponRepository extends JpaRepository<CoCWeaponEntity, UUID> {

    boolean existsBySkillUsedId(UUID id);

    boolean existsByAmmoId(UUID id);
}
