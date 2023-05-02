package com.olio.lutemon.LutemonClasses;

import java.util.ArrayList;

public class Opponent {
    private String name;
    private int image;
    private int XP;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();

    public Opponent(String name, int image, int XP) {
        this.name = name;
        this.image = image;
        this.XP = XP;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public ArrayList<Lutemon> getLutemons() {
        return lutemons;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setLutemons(ArrayList<Lutemon> lutemons) {
        this.lutemons = lutemons;
    }

    public int getXP(){
        return this.XP;
    }
}
