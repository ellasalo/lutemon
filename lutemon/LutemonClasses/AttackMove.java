package com.olio.lutemon.LutemonClasses;

public class AttackMove extends Move{
    private int power;

    public AttackMove(String name, int power) {
        super(name, "hyökkäysliike");
        this.power = power;
    }

    public int getPower(){
        return this.power;
    }

}
