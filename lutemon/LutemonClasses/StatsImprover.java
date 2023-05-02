package com.olio.lutemon.LutemonClasses;

public class StatsImprover extends Move{
    private double attackImproveNumber;
    private double defenceImproveNumber;

    public StatsImprover(String name, double attackImproveNumber, double defenceImproveNumber) {
        super(name, "statsien parantaja");
        this.attackImproveNumber = attackImproveNumber;
        this.defenceImproveNumber = defenceImproveNumber;
    }

    public double getAttackImproveNumber() {
        return attackImproveNumber;
    }

    public double getDefenceImproveNumber() {
        return defenceImproveNumber;
    }
}
