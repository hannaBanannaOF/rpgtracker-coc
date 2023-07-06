package com.hbsites.rpgtracker.coc.enumeration;

public enum ESkillKind {
    INTERPERSONAL("Interpessoal"),
    INVESTIGATION("Investigação"),
    COMBAT("Combate"),
    COMMON("Comum");

    private final String desc;

    private ESkillKind(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return this.desc;
    }
}
