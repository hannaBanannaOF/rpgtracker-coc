package com.hbsites.rpgtracker.coc.enumeration;

public enum ESkillPointCalculationRule {

    EDU4("EDU x 4"),
    EDU2_INT2("EDU x 2 + INT x 2"),
    EDU2_APP2("EDU x 2 + APP x 2"),
    EDU2_STR2("EDU x 2 + STR x 2"),
    EDU2_DEX2("EDU x 2 + DEX x 2"),
    EDU2_STR_OR_DEX_2("EDU x 2 + (STR x 2 or DEX x 2)"),
    EDU2_APP_OR_DEX_2("EDU x 2 + (APP x 2 or DEX x 2)"),
    EDU2_STR_OR_DEX_OR_APP_2("EDU x 2 + (STR x 2 or DEX x 2 or APP x 2)");

    private final String desc;

    private ESkillPointCalculationRule(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return this.desc;
    }
}
