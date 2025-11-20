package com.ZUNr1.model;

public class Attributes {
    private int health;
    private int attack;
    private int realityDefense;
    private int mentalDefense;
    private int technique;
    // 添加无参构造函数（Jackson需要）
    //json反序列化的本质是：将一段文本（JSON字符串）转换成一个内存中的Java对象。
    //他需要创建一个对象实例，但是问题是他不知道怎么构建一个对象实例
    //我们要给他无参构造器来使用
    //然后，json通过反射，找到对应字段，把数据填入进去
    public Attributes() {
        // Jackson会通过setter方法设置值
    }

    public Attributes(int health, int attack, int realityDefense, int mentalDefense, int technique) {
        this.health = health;
        this.attack = attack;
        this.realityDefense = realityDefense;
        this.mentalDefense = mentalDefense;
        this.technique = technique;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMentalDefense() {
        return mentalDefense;
    }

    public void setMentalDefense(int mentalDefense) {
        this.mentalDefense = mentalDefense;
    }

    public int getRealityDefense() {
        return realityDefense;
    }

    public void setRealityDefense(int realityDefense) {
        this.realityDefense = realityDefense;
    }

    public int getTechnique() {
        return technique;
    }

    public void setTechnique(int technique) {
        this.technique = technique;
    }
}

