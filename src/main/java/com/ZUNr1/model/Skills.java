package com.ZUNr1.model;

import java.util.ArrayList;
import java.util.List;

public class Skills {
    private String arcaneSkill1;
    private String arcaneSkill2;
    private String arcaneSkill3;
    private List<String> extraSkills;
    public static class SkillsBuilder{
        private String arcaneSkill1;
        private String arcaneSkill2;
        private String arcaneSkill3;
        private List<String> extraSkills;
        public SkillsBuilder(){

            this.arcaneSkill1 = "";
            //对于字符串String，应该设为空值，避免空指针
            this.arcaneSkill2 = "";
            this.arcaneSkill3 = "";
            this.extraSkills = new ArrayList<>();
        }
        public SkillsBuilder arcaneSkill1(String arcaneSkill1){
            this.arcaneSkill1 = arcaneSkill1 == null ? "" : arcaneSkill1;
            return this;
        }
        public SkillsBuilder arcaneSkill2(String arcaneSkill2){
            this.arcaneSkill2 = arcaneSkill2 == null ? "" : arcaneSkill2;
            return this;
        }
        public SkillsBuilder arcaneSkill3(String arcaneSkill3){
            this.arcaneSkill3 = arcaneSkill3 == null ? "" : arcaneSkill3;
            return this;
        }
        public SkillsBuilder addExtraSkills(String extraSkill){
            if (extraSkill != null && !extraSkill.trim().isEmpty()){
                extraSkills.add(extraSkill.trim());
            }
            return this;
        }

        public Skills build(){
            return new Skills(this);
        }
    }
    private Skills(SkillsBuilder skillsBuilder){
        this.arcaneSkill1 = skillsBuilder.arcaneSkill1;
        this.arcaneSkill2 = skillsBuilder.arcaneSkill2;
        this.arcaneSkill3 = skillsBuilder.arcaneSkill3;
        this.extraSkills = new ArrayList<>(skillsBuilder.extraSkills);
        //this.extraSkills = skillsBuilder.extraSkills;
        //这行是错误的，因为这样skillsBuilder和Skills会共享同一个对象，不好
        //这叫防御性拷贝
    }

    public String getArcaneSkill1() {
        return arcaneSkill1;
    }

    public String getArcaneSkill2() {
        return arcaneSkill2;
    }

    public String getArcaneSkill3() {
        return arcaneSkill3;
        //知道吗，String类型不需要防御性拷贝，因为String类型不可变
    }

    public List<String> getExtraSkills() {
        return new ArrayList<>(extraSkills);
        //同样，这返回新建的副本，也是防御性拷贝，这也是防止被修改
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("一技能：%s\n二技能：%s\n大招：%s\n",arcaneSkill1,arcaneSkill2,arcaneSkill3));
        if (!extraSkills.isEmpty()){
            sb.append("额外技能：").append(String.join("\n",extraSkills));
        }
        return sb.toString();
    }
}
