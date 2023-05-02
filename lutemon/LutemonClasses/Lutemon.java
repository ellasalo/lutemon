package com.olio.lutemon.LutemonClasses;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Lutemon implements Serializable {
    protected int image;
    protected String lutemon;
    protected String nickname;
    protected String type;
    protected String weakness;
    protected String description;
    protected String evolution;
    protected Stats stats;
    protected ArrayList<Move> moves;
    protected Statistics statistics;

    public Lutemon(int image, String lutemon, String nickname, String type, String weakness, String description, String evolution, Stats stats, ArrayList<Move> moves, Statistics statistics) {
        this.image = image;
        this.lutemon = lutemon;
        this.nickname = nickname;
        this.type = type;
        this.weakness = weakness;
        this.description = description;
        this.evolution = evolution;
        this.stats = stats;
        this.moves = moves;
        this.statistics = statistics;
    }

    public void restLutemon() {
        this.getStats().setLeftHP(this.getStats().getMaxHP());
    }

    public String getNickname() {
        return nickname;
    }

    public String getDescription() {
        return description;
    }

    public String getEvolution() {
        return evolution;
    }

    public ArrayList<Move> getMoves() {
        return moves;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public int getImage() {
        return image;
    }

    public String getLutemon() {
        return lutemon;
    }

    public String getType() {
        return type;
    }

    public String getWeakness() {
        return weakness;
    }

    public Stats getStats() {
        return stats;
    }

    public abstract void levelUP();

    public abstract void evolve();

    public abstract void learnMove();

    public void takeDamage(int damage) {
        if (this.stats.getLeftHP() - damage >= 0) {
            this.stats.setLeftHP(this.stats.getLeftHP() - damage);
        } else {
            this.stats.setLeftHP(0);
        }
    }


}
