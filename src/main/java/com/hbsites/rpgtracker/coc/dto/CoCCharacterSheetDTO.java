package com.hbsites.rpgtracker.coc.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class CoCCharacterSheetDTO {
    private @NonNull UUID id;
    private @NonNull UUID coreId;
    private @NonNull CoCCharacterSheetBasicInfoDTO basicInfo;
    private @NonNull CoCCharacterSheetBasicAttributesDTO basicAttributes;
    private @NonNull CoCCharacterSheetCalculatedAttributesDTO calculatedAttributes;
    private List<CoCCharacterSheetSkillDTO> skills = new ArrayList<>();
    private @NonNull List<CoCPulpTalentDTO> pulpTalents;
    private CoCOccupationDetailDTO occupation;
    private @NonNull List<CoCCharacterSheetWeaponDTO> weapons;

    @RequiredArgsConstructor
    @Data
    public static class CoCCharacterSheetBasicInfoDTO {
        private @NonNull String characterName;
        private String playerName;
        private @NonNull Boolean pulpCthulhu;
        private @NonNull Integer age;
        private @NonNull String sex;
        private @NonNull String birthplace;
        private @NonNull String residence;
        private @NonNull String pulpArchetype;
    }

    @RequiredArgsConstructor
    @Data
    public static class CoCCharacterSheetBasicAttributesDTO {
        private @NonNull Integer strength;
        private @NonNull Integer constitution;
        private @NonNull Integer size;
        private @NonNull Integer dexterity;
        private @NonNull Integer appearance;
        private @NonNull Integer intelligence;
        private @NonNull Integer power;
        private @NonNull Integer education;
        private @NonNull Integer luck;
    }

    @RequiredArgsConstructor
    @Data
    public static class CoCCharacterSheetCalculatedAttributesDTO {
        private @NonNull Integer moveRate;
        private @NonNull Integer healthPoints;
        private @NonNull Integer magicPoints;
        private @NonNull Integer sanity;
        private @NonNull Integer startingSanity;
        private @NonNull Integer maximumHealthPoints;
        private @NonNull Integer maximumMagicPoints;
        private @NonNull Integer maximumSanity;
        private @NonNull Integer build;
        private @NonNull String bonusDamage;
        private @NonNull Boolean majorWounds;
        private @NonNull Boolean temporaryInsanity;
        private @NonNull Boolean indefiniteInsanity;
        private @NonNull Integer occupationalSkillPoints;
        private @NonNull Integer personalInterestSkillPoints;
        private @NonNull Integer dodge;
    }
}
