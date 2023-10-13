package com.hbsites.rpgtracker.coc.config.aop;

import com.hbsites.hbsitescommons.commons.interfaces.CRUDService;
import com.hbsites.hbsitescommons.commons.utils.UserUtils;
import com.hbsites.rpgtracker.coc.entity.AmmoEntity;
import com.hbsites.rpgtracker.coc.entity.OccupationEntity;
import com.hbsites.rpgtracker.coc.entity.PulpTalentEntity;
import com.hbsites.rpgtracker.coc.entity.SkillEntity;
import com.hbsites.rpgtracker.coc.entity.SpellEntity;
import com.hbsites.rpgtracker.coc.entity.WeaponEntity;
import com.hbsites.rpgtracker.coc.repository.AmmoRepository;
import com.hbsites.rpgtracker.coc.repository.OccupationRepository;
import com.hbsites.rpgtracker.coc.repository.PulpTalentRepository;
import com.hbsites.rpgtracker.coc.repository.SkillRepository;
import com.hbsites.rpgtracker.coc.repository.SpellRepository;
import com.hbsites.rpgtracker.coc.repository.WeaponRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Aspect
@Component
public class EditableResourceAspect {

    @Lazy
    @Autowired
    private AmmoRepository ammoRepository;
    @Lazy
    @Autowired
    private OccupationRepository occupationRepository;
    @Lazy
    @Autowired
    private PulpTalentRepository pulpTalentRepository;
    @Lazy
    @Autowired
    private SkillRepository skillRepository;
    @Lazy
    @Autowired
    private SpellRepository spellRepository;
    @Lazy
    @Autowired
    private WeaponRepository weaponRepository;

    @Around("@annotation(EditableResource)")
    public Object logExecTime(ProceedingJoinPoint joinPoint) throws Throwable {
        EditableResource annotation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(EditableResource.class);
        Optional ret = getObjectFromRepo(joinPoint.getArgs()[0], annotation);
        if (ret.isPresent()) {
            Object val = ret.get();
            Field f = val.getClass().getDeclaredField("creatorId");
            f.setAccessible(true);
            if (!UserUtils.getUserUUID().equals(f.get(val))) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        } else {
            return joinPoint.proceed();
        }
        return joinPoint.proceed();
    }

    private Optional getObjectFromRepo(Object id, EditableResource annotation) {
        if (annotation.clazz().equals(AmmoEntity.class)) {
            return ammoRepository.findById(((UUID) id));
        }
        if (annotation.clazz().equals(OccupationEntity.class)) {
            return occupationRepository.findById(((UUID) id));
        }
        if (annotation.clazz().equals(PulpTalentEntity.class)) {
            return pulpTalentRepository.findById(((UUID) id));
        }
        if (annotation.clazz().equals(SkillEntity.class)) {
            return skillRepository.findById(((UUID) id));
        }
        if (annotation.clazz().equals(SpellEntity.class)) {
            return spellRepository.findById(((UUID) id));
        }
        if (annotation.clazz().equals(WeaponEntity.class)) {
            return weaponRepository.findById(((UUID) id));
        }
        return Optional.empty();
    }
}
