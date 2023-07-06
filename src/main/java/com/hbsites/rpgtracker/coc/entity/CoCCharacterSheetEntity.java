package com.hbsites.rpgtracker.coc.entity;

import com.hbsites.hbsitescommons.dto.CharacterSheetBasicInfoDTO;
import com.hbsites.hbsitescommons.dto.CharacterSheetListingDTO;
import com.hbsites.hbsitescommons.entity.BaseEntity;
import com.hbsites.hbsitescommons.entity.RabbitBaseEntity;
import com.hbsites.hbsitescommons.interfaces.EventProducerInterface;
import com.hbsites.rpgtracker.coc.dto.CoCCharacterSheetDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import org.apache.commons.lang3.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "character_sheet")
public class CoCCharacterSheetEntity extends RabbitBaseEntity<CharacterSheetListingDTO<CoCSessionEntity>, CoCCharacterSheetDTO, List<CharacterSheetBasicInfoDTO>> {

    @Column(name = "id", columnDefinition = "uuid")
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "core_character_sheet_id", columnDefinition = "uuid")
    private UUID coreCharacterSheetId;

    @Column(name = "pulp_cthulhu", columnDefinition = "boolean", nullable = false)
    private Boolean pulpCthulhu;

    @Column(name = "age", columnDefinition = "integer", nullable = false)
    private Integer age;

    @Column(name = "sex", columnDefinition = "varchar(50)", nullable = false)
    private String sex;

    @Column(name = "birthplace", columnDefinition = "varchar(100)", nullable = false)
    private String birthplace;

    @Column(name = "residence", columnDefinition = "varchar(100)", nullable = false)
    private String residence;

    @Column(name = "strength", columnDefinition = "integer", nullable = false)
    private Integer strength;

    @Column(name = "constitution", columnDefinition = "integer", nullable = false)
    private Integer constitution;

    @Column(name = "size", columnDefinition = "integer", nullable = false)
    private Integer size;

    @Column(name = "dexterity", columnDefinition = "integer", nullable = false)
    private Integer dexterity;

    @Column(name = "appearance", columnDefinition = "integer", nullable = false)
    private Integer appearance;

    @Column(name = "intelligence", columnDefinition = "integer", nullable = false)
    private Integer intelligence;

    @Column(name = "power", columnDefinition = "integer", nullable = false)
    private Integer power;

    @Column(name = "education", columnDefinition = "integer", nullable = false)
    private Integer education;

    @Column(name = "luck", columnDefinition = "integer", nullable = false)
    private Integer luck;

    @Column(name = "move_rate", columnDefinition = "integer", nullable = false)
    private Integer moveRate;

    @Column(name = "health_points", columnDefinition = "integer", nullable = false)
    private Integer healthPoints;

    @Column(name = "magic_points", columnDefinition = "integer", nullable = false)
    private Integer magicPoints;

    @Column(name = "sanity", columnDefinition = "integer", nullable = false)
    private Integer sanity;

    @Column(name = "starting_sanity", columnDefinition = "integer", nullable = false)
    private Integer startingSanity;

    @Column(name = "maximum_health_points", columnDefinition = "integer", nullable = false)
    private Integer maximumHealthPoints;

    @Column(name = "maximum_magic_points", columnDefinition = "integer", nullable = false)
    private Integer maximumMagicPoints;

    @Column(name = "maximum_sanity", columnDefinition = "integer", nullable = false)
    private Integer maximumSanity;

    @Column(name = "build", columnDefinition = "integer", nullable = false)
    private Integer build;

    @Column(name = "bonus_damage", columnDefinition = "varchar(10)", nullable = false)
    private String bonusDamage;

    @Column(name = "dodge", columnDefinition = "integer", nullable = false)
    private Integer dodge;

    @Column(name = "dodge_improvement_check", columnDefinition = "boolean", nullable = false)
    private Boolean dodgeImprovementCheck;

    @Column(name = "language_own", columnDefinition = "integer", nullable = false)
    private Integer languageOwn;

    @Column(name = "language_own_improvement_check", columnDefinition = "boolean", nullable = false)
    private Boolean languageOwnImprovementCheck;

    @Column(name = "occupational_skill_points", columnDefinition = "integer", nullable = false)
    private Integer occupationalSkillPoints;

    @Column(name = "personal_interest_skill_points", columnDefinition = "integer", nullable = false)
    private Integer personalInterestSkillPoints;

    @Column(name = "major_wounds", columnDefinition = "boolean", nullable = false)
    private Boolean majorWounds;

    @Column(name = "temporary_insanity", columnDefinition = "boolean", nullable = false)
    private Boolean temporaryInsanity;

    @Column(name = "indefinite_insanity", columnDefinition = "boolean", nullable = false)
    private Boolean indefiniteInsanity;

    @Column(name = "credit_rating", columnDefinition = "integer", nullable = false)
    private Integer creditRating;

    @Column(name = "cthulhu_mythos", columnDefinition = "integer", nullable = false)
    private Integer cthulhuMythos;

    @Column(name = "pulp_archetype", columnDefinition = "varchar(50)")
    private String pulpArchetype;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "characterSheet")
    private List<CoCCharacterSheetSkillEntity> skills;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "characterSheet")
    private List<CoCCharacterSheetWeaponEntity> weapons;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "pulp_talents_character_sheet",
            joinColumns = @JoinColumn(name = "character_sheet_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "pulp_talent_id", referencedColumnName = "id"))
    private List<CoCPulpTalentEntity> pulpTalents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "occupation_id", referencedColumnName = "id")
    private CoCOccupationEntity occupation;

    @Override
    public CharacterSheetListingDTO<CoCSessionEntity> toListDTO() {
        throw new NotImplementedException();
    }

    @Override
    public CoCCharacterSheetDTO toDetailDTO() {
        CoCCharacterSheetDTO dto = new CoCCharacterSheetDTO(this.getId(), this.getCoreCharacterSheetId(),
                new CoCCharacterSheetDTO.CoCCharacterSheetBasicInfoDTO("", this.getPulpCthulhu(), this.getAge(),
                        this.getSex(), this.getBirthplace(), this.getResidence(), this.getPulpArchetype()),
                new CoCCharacterSheetDTO.CoCCharacterSheetBasicAttributesDTO(this.getStrength(), this.getConstitution(), this.getSize(),
                        this.getDexterity(), this.getAppearance(), this.getIntelligence(), this.getPower(), this.getEducation(), this.getLuck()),
                new CoCCharacterSheetDTO.CoCCharacterSheetCalculatedAttributesDTO(this.getMoveRate(), this.getHealthPoints(), this.getMagicPoints(), this.getSanity(),
                        this.getStartingSanity(), this.getMaximumHealthPoints(), this.getMaximumMagicPoints(), this.getMaximumSanity(), this.getBuild(),
                        this.getBonusDamage(), this.getMajorWounds(), this.getTemporaryInsanity(), this.getIndefiniteInsanity(), this.getOccupationalSkillPoints(),
                        this.getPersonalInterestSkillPoints(), this.getDodge()),
                this.getPulpTalents() != null ? this.getPulpTalents().stream().map(CoCPulpTalentEntity::toListDTO).collect(Collectors.toList()) : new ArrayList<>(),
                this.getWeapons() != null ? this.getWeapons().stream().map(CoCCharacterSheetWeaponEntity::toListDTO).collect(Collectors.toList()) : new ArrayList<>()
        );
        dto.setOccupation(this.getOccupation() != null ? this.getOccupation().toDetailDTO() : null);
        return dto;
    }

    @Override
    public CharacterSheetListingDTO<CoCSessionEntity> toListDTO(EventProducerInterface<List<CharacterSheetBasicInfoDTO>> template) {
        throw new NotImplementedException();
    }

    @Override
    public CoCCharacterSheetDTO toDetailDTO(EventProducerInterface<List<CharacterSheetBasicInfoDTO>> template) {
        CoCCharacterSheetDTO dto = toDetailDTO();
        CharacterSheetBasicInfoDTO info = template.getFromRabbitMQ(List.of(getCoreCharacterSheetId())).stream().findFirst().orElse(new CharacterSheetBasicInfoDTO());
        if (info.getPlayer() != null) {
            dto.getBasicInfo().setPlayerName(info.getPlayer().getDisplayName());
        }
        if (info.getCharacterName() != null) {
            dto.getBasicInfo().setCharacterName(info.getCharacterName());
        }
        return dto;
    }
}
