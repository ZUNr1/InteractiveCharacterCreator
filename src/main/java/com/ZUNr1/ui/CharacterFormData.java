package com.ZUNr1.ui;

import com.ZUNr1.enums.Afflatus;
import com.ZUNr1.enums.DamageType;
import com.ZUNr1.enums.Gender;
import com.ZUNr1.enums.SkillType;

import java.util.List;
import java.util.Map;

public class CharacterFormData {
    // 基本信息
    private String id;
    private String name;
    private Gender gender;
    private Afflatus afflatus;
    private DamageType damageType;
    private int rarity;  // 改为基本类型

    // 属性信息
    private int health;
    private int attack;
    private int realityDefense;
    private int mentalDefense;
    private int technique;

    // 技能信息
    private Map<String, String> skillNames; // 技能名称 Map<"神秘术I", "技能名">
    private Map<String, Map<String, String>> skillDescribes; // 技能描述 Map<"神秘术I", Map<"一星牌", "描述">>
    private Map<String, Map<String, String>> skillStories;   // 技能故事
    private Map<String, Map<String, SkillType>> skillTypes;     // 技能类型

    // 传承信息
    private String inheritanceName;
    private String basicInheritance;
    private String firstInheritance;
    private String secondInheritance;
    private String thirdInheritance;

    // 塑造信息
    private String portraitDescribe;
    private String firstPortrait;
    private String secondPortrait;
    private String thirdPortrait;
    private String fourthPortrait;
    private String fifthPortrait;

    // 专有名词
    private Map<String, String> usedTerms; // Map<"术语名", "术语描述">

    // 构造器
    public CharacterFormData() {}

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public Gender getGender() {
        return gender;
    }

    public Afflatus getAfflatus() {
        return afflatus;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public int getRarity() {
        return rarity;
    }

    public int getAttack() {
        return attack;
    }

    public int getHealth() {
        return health;
    }

    public int getMentalDefense() {
        return mentalDefense;
    }

    public int getRealityDefense() {
        return realityDefense;
    }

    public int getTechnique() {
        return technique;
    }

    public String getPortraitDescribe() {
        return portraitDescribe;
    }

    public String getFirstPortrait() {
        return firstPortrait;
    }

    public String getSecondPortrait() {
        return secondPortrait;
    }

    public String getThirdPortrait() {
        return thirdPortrait;
    }

    public String getFourthPortrait() {
        return fourthPortrait;
    }

    public String getFifthPortrait() {
        return fifthPortrait;
    }

    public String getInheritanceName() {
        return inheritanceName;
    }

    public String getBasicInheritance() {
        return basicInheritance;
    }

    public String getFirstInheritance() {
        return firstInheritance;
    }

    public String getSecondInheritance() {
        return secondInheritance;
    }

    public String getThirdInheritance() {
        return thirdInheritance;
    }

    public Map<String, String> getSkillNames() {
        return skillNames;
    }

    public Map<String, Map<String, String>> getSkillDescribes() {
        return skillDescribes;
    }

    public Map<String, Map<String, String>> getSkillStories() {
        return skillStories;
    }

    public Map<String, Map<String, SkillType>> getSkillTypes() {
        return skillTypes;
    }

    public Map<String, String> getUsedTerms() {
        return usedTerms;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setAfflatus(Afflatus afflatus) {
        this.afflatus = afflatus;
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setRealityDefense(int realityDefense) {
        this.realityDefense = realityDefense;
    }

    public void setMentalDefense(int mentalDefense) {
        this.mentalDefense = mentalDefense;
    }

    public void setTechnique(int technique) {
        this.technique = technique;
    }

    public void setInheritanceName(String inheritanceName) {
        this.inheritanceName = inheritanceName;
    }

    public void setBasicInheritance(String basicInheritance) {
        this.basicInheritance = basicInheritance;
    }

    public void setFirstInheritance(String firstInheritance) {
        this.firstInheritance = firstInheritance;
    }

    public void setSecondInheritance(String secondInheritance) {
        this.secondInheritance = secondInheritance;
    }

    public void setThirdInheritance(String thirdInheritance) {
        this.thirdInheritance = thirdInheritance;
    }

    public void setPortraitDescribe(String portraitDescribe) {
        this.portraitDescribe = portraitDescribe;
    }

    public void setFirstPortrait(String firstPortrait) {
        this.firstPortrait = firstPortrait;
    }

    public void setSecondPortrait(String secondPortrait) {
        this.secondPortrait = secondPortrait;
    }

    public void setThirdPortrait(String thirdPortrait) {
        this.thirdPortrait = thirdPortrait;
    }

    public void setFourthPortrait(String fourthPortrait) {
        this.fourthPortrait = fourthPortrait;
    }

    public void setFifthPortrait(String fifthPortrait) {
        this.fifthPortrait = fifthPortrait;
    }

    public void setSkillNames(Map<String, String> skillNames) {
        this.skillNames = skillNames;
    }

    public void setSkillDescribes(Map<String, Map<String, String>> skillDescribes) {
        this.skillDescribes = skillDescribes;
    }

    public void setSkillStories(Map<String, Map<String, String>> skillStories) {
        this.skillStories = skillStories;
    }

    public void setSkillTypes(Map<String, Map<String, SkillType>> skillTypes) {
        this.skillTypes = skillTypes;
    }

    public void setUsedTerms(Map<String, String> usedTerms) {
        this.usedTerms = usedTerms;
    }
}