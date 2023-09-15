package com.hbsites.rpgtracker.coc.dto;

import com.hbsites.rpgtracker.coc.enumeration.ESpellCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpellDetailDTO {
    private UUID id;
    private String name;
    private String monsterKnowledge;
    private String cost;
    private String conjuringTime;
    private String description;
    private String visceralForm;
    private String alternativeNames;
    private List<CategoryDTO> categories;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CategoryDTO {
        private UUID id;
        private ESpellCategory category;
    }
}
