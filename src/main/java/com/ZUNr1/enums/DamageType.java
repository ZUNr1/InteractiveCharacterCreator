package com.ZUNr1.enums;

public enum DamageType {
    REALITY(1,"现实创伤"),
    MENTAL(-1,"精神创伤"),
    GENESIS(0,"本源创伤");
    private final int type;
    private final String chineseName;
    private DamageType(int type,String chineseName){
        this.type = type;
        this.chineseName = chineseName;
    }
    public DamageType fromType(int type){
        for (DamageType damageType : values()){
            if (damageType.type == type){
                return damageType;
            }
        }
        throw new IllegalArgumentException("无效的伤害类型");
    }

    public int getType() {
        return type;
    }

    public String getChineseName() {
        return chineseName;
    }
}

