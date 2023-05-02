package com.olio.lutemon.LutemonClasses;

import java.io.Serializable;

public class Stats implements Serializable {
    private int level;
    private int maxHP;
    private int leftHP;
    private int attack;
    private int defence;
    private int speed;
    private int XP;
    private int nextLevelXP;

    public Stats(int level, int maxHP, int attack, int defence, int speed) {
        this.level = level;
        this.maxHP = maxHP;
        this.leftHP = maxHP;
        this.attack = attack;
        this.defence = defence;
        this.speed = speed;
        this.XP = 0;
        this.nextLevelXP = 6;
    }

    public int getLevel() {
        return level;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getLeftHP() {
        return leftHP;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getSpeed() {
        return speed;
    }

    public int getXP() {
        return XP;
    }

    public int getNextLevelXP() {
        return nextLevelXP;
    }

    public String getStatText(){
        return "Taso: " + this.level + "\nHP: " + this.leftHP + "/" +   this.maxHP + "\nHyökkäys: " + this.attack + "\nPuolustus: " + this.defence + "\nNopeus: " + this.speed;
    }

    public void setLevelUp(int levelUP, int maxHPUP, int attackUP, int defenceUP, int speedUP) {
        this.level = this.level + levelUP;
        this.maxHP = this.maxHP + maxHPUP;
        this.attack = this.attack + attackUP;
        this.defence = this.defence + defenceUP;
        this.speed = this.speed + speedUP;
        this.nextLevelXP = (int) Math.pow(this.level + 1, 2) + this.level + 1;
    }

    public void setLeftHP(int leftHP) {
        this.leftHP = leftHP;
    }

    public void addXP(int plusXP) {
        this.XP = this.XP + plusXP;
    }

    public void setUpgradedStats() {
        this.maxHP = this.maxHP + 30;
        this.attack = this.attack + 30;
        this.defence = this.defence + 30;
        this.speed = this.speed + 30;
    }

}
