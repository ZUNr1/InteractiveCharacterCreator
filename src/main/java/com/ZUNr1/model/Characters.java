package com.ZUNr1.model;

import com.ZUNr1.enums.Afflatus;
import com.ZUNr1.enums.DamageType;
import com.ZUNr1.enums.Gender;

public final class Characters extends Individual{
    private Inheritance inheritance;
    private Portrait portrait;
    private int birthday;
    private int rarity;
    public static class CharactersBuilder extends Builder{
        //Builder内部类设计为静态主要是为了不依靠外部类的实例，
        // 就是即使我没有实例化外部的类，也可以直接调用Builder，最后使用Build方法才会实例化外部对象
        //你想，我们要实例化外部类，构造器让我们用Builder，所以Builder一定是先于外部类实例化的
        private Inheritance inheritance;
        private Portrait portrait;
        private int birthday;
        private int rarity;
        public CharactersBuilder(String id, String name, Gender gender, int level){
            super(id, name, gender, level);
            //继承
        }
        @Override
        public CharactersBuilder attributes(Attributes attributes){
            this.attributes = attributes;
            return this;
            //返回this可以实现链式调用，一串代码完成所有构造
        }
        @Override
        public CharactersBuilder afflatus(Afflatus afflatus){
            this.afflatus = afflatus;
            return this;
        }
        @Override
        public  CharactersBuilder damageType(DamageType damageType){
            this.damageType = damageType;
            return this;
        }
        @Override
        public CharactersBuilder skills(Skills skills){
            this.skills = skills;
            return this;
        }
        public CharactersBuilder inheritance(Inheritance inheritance){
            this.inheritance = inheritance;
            return this;
        }
        public CharactersBuilder portrait(Portrait portrait){
            this.portrait = portrait;
            return this;
        }
        public CharactersBuilder birthday(int birthday){
            this.birthday = birthday;
            return this;
        }
        public CharactersBuilder rarity(int rarity){
            rarityValidate(rarity);
            this.rarity = rarity;
            return this;
        }
        //注意注意，Builder类里面必须要build方法，
        // 链式构造的时候build方法放在最后，Builder写了一大串，用build直接用在产生的对象上
        @Override
        public Characters build(){
            return new Characters(this);
        }
        private void rarityValidate(int rarity){
            if (rarity < 2 || rarity > 6){
                throw new IllegalArgumentException("稀有度超出范围");
            }
        }
    }
    //构造器传入的类型是父类Builder的子类实现CharacterBuilder，运用多态的思想
    private Characters(CharactersBuilder charactersBuilder){
        //使用private，强制使用builder来构造
        super(charactersBuilder);
        //子类cB含有父类B的所有字段，是父类的具体实现，所以传入cB而不是B
        this.birthday = charactersBuilder.birthday;
        this.rarity = charactersBuilder.rarity;
        //对额外字段赋值
    }

    public Inheritance getInheritance() {
        return inheritance;
    }

    public Portrait getPortrait() {
        return portrait;
    }

    public int getBirthday() {
        return birthday;
    }

    public int getRarity() {
        return rarity;
    }


}