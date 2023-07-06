package com.hbsites.rpgtracker.coc.enumeration;

public enum ESkillRarity {
    COMMON("Comum"),
    ANTIQUE("1920 Era"),
    MODERN("Moderna"),
    PULP("Pulp Cthulhu");

    private final String desc;

    private ESkillRarity(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return this.desc;
    }
}
