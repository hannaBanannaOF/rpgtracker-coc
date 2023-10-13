package com.hbsites.rpgtracker.coc.service;

import com.hbsites.hbsitescommons.commons.interfaces.CRUDService;
import com.hbsites.rpgtracker.coc.dto.OccupationDetailDTO;
import com.hbsites.rpgtracker.coc.dto.SpellDetailDTO;
import com.hbsites.rpgtracker.coc.dto.SpellListingDTO;
import com.hbsites.rpgtracker.coc.entity.AmmoEntity;
import com.hbsites.rpgtracker.coc.entity.SkillEntity;
import com.hbsites.rpgtracker.coc.entity.SpellCategoryEntity;
import com.hbsites.rpgtracker.coc.entity.SpellEntity;
import com.hbsites.rpgtracker.coc.repository.SpellCategoryRepository;
import com.hbsites.rpgtracker.coc.repository.SpellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SpellService implements CRUDService<UUID, SpellListingDTO, SpellDetailDTO, SpellDetailDTO> {

    @Autowired
    @Lazy
    private SpellRepository spellRepository;

    @Autowired
    @Lazy
    private SpellCategoryRepository spellCategoryRepository;

    @Override
    public Page<SpellListingDTO> getAll(int page) {
        PageRequest pageRequest = PageRequest.of(
                page,
                20,
                Sort.by("name"));
        return spellRepository.findAll(pageRequest).map(SpellEntity::toListDTO);
    }

    @Override
    public SpellDetailDTO create(SpellDetailDTO dto) {
        SpellEntity e = new SpellEntity();
        e.setName(dto.getName());
        e.setCost(dto.getCost());
        e.setDescription(dto.getDescription());
        e.setAlternativeNames(dto.getAlternativeNames());
        e.setConjuringTime(dto.getConjuringTime());
        e.setMonsterKnowledge(dto.getMonsterKnowledge());
        e.setVisceralForm(dto.getVisceralForm());
        e = spellRepository.save(e);
        if (dto.getCategories() != null) {
            for (SpellDetailDTO.CategoryDTO category : dto.getCategories()) {
                SpellCategoryEntity spellcate = new SpellCategoryEntity();
                spellcate.setSpell(e);
                spellcate.setCategory(category.getCategory());
                spellCategoryRepository.save(spellcate);
            }
        }
        return e.toDetailDTO();
    }

    @Override
    public void deleteById(UUID id) {
        spellRepository.deleteById(id);
    }

    @Override
    public SpellDetailDTO getById(UUID id) {
        return findEntityOrElseThrow(id).toDetailDTO();
    }

    @Override
    public SpellDetailDTO update(UUID id, SpellDetailDTO payload) {
        SpellEntity e = findEntityOrElseThrow(id);
        e.setName(payload.getName());
        e.setCost(payload.getCost());
        e.setDescription(payload.getDescription());
        e.setAlternativeNames(payload.getAlternativeNames());
        e.setConjuringTime(payload.getConjuringTime());
        e.setMonsterKnowledge(payload.getMonsterKnowledge());
        e.setVisceralForm(payload.getVisceralForm());
        if (e.getCategories() != null && !e.getCategories().isEmpty()) {
            // filtrar pelo que existe no dto
            List<SpellCategoryEntity> mantain = new ArrayList<>(e.getCategories().stream().filter(exist -> payload.getCategories().stream().anyMatch(payloadV -> exist.getId().equals(payloadV.getId()))).toList());
            List<SpellCategoryEntity> delete = e.getCategories().stream().filter(cat -> !mantain.contains(cat)).toList();
            spellCategoryRepository.deleteAll(delete);

            //atualizar valores dos existentes
            for (SpellCategoryEntity category : mantain) {
                payload.getCategories().stream().filter(cat -> category.getId().equals(cat.getId())).findFirst().ifPresent(newC -> category.setCategory(newC.getCategory()));
            }
            //inserir novos
            List<SpellDetailDTO.CategoryDTO> newValues = payload.getCategories().stream().filter(payloadV -> payloadV.getId() == null).toList();
            for (SpellDetailDTO.CategoryDTO val : newValues) {
                SpellCategoryEntity newCategory = new SpellCategoryEntity();
                newCategory.setCategory(val.getCategory());
                newCategory.setSpell(e);
                mantain.add(newCategory);
            }
            e.setCategories(spellCategoryRepository.saveAll(mantain));
        } else {
            List<SpellCategoryEntity> mantain = new ArrayList<>();
            for (SpellDetailDTO.CategoryDTO val : payload.getCategories()) {
                SpellCategoryEntity newCategory = new SpellCategoryEntity();
                newCategory.setCategory(val.getCategory());
                newCategory.setSpell(e);
                mantain.add(newCategory);
            }
            e.setCategories(spellCategoryRepository.saveAll(mantain));
        }
        e = spellRepository.save(e);
        return e.toDetailDTO();
    }

    private SpellEntity findEntityOrElseThrow(UUID uuid) {
        return spellRepository.findById(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
