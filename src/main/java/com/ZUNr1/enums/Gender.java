package com.ZUNr1.enums;

public enum Gender {
    MAN("男"), WOMAN("女"), OTHER("其他");
    private String chineseName;
    private Gender(String chineseName){
        this.chineseName = chineseName;
    }

    public String getChineseName() {
        return chineseName;
    }
}

