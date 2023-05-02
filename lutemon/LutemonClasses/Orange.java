package com.olio.lutemon.LutemonClasses;

import com.olio.lutemon.R;

import java.util.ArrayList;
import java.util.Random;

public class Orange extends Lutemon{
    public Orange(String nickname) {
        super(R.drawable.pienioranssi, "Undula", nickname,"Oranssi", "Valkoinen", "Undula on undulaattia muistuttava lintu-Lutemon. Undula on luonteeltaan reipas ja se rakastaa seikkailuja. Undulaa täytyy käsitellä hanskat kädessä, sillä sen ruumiinlämpö on 70 astetta", "Tsembalo", new Stats(1, 12, 8, 2, 12), new ArrayList<>(), new Statistics(0,0,0));
        moves.add(new AttackMove("Pure", 12));
        moves.add(new AttackMove("Töni", 7));
        moves.add(new StatsImprover("Väistele", 1, 1.2));
        moves.add(new StatsImprover("Ryhdistäydy", 1.3, 1));
    }

    public Orange(int image, String lutemon, String nickname, String type, String weakness, String description, String evolution, Stats stats, ArrayList<Move> moves, Statistics statistics) {
        super(image, lutemon, nickname, type, weakness, description, evolution, stats, moves, statistics);
    }

    @Override
    public void levelUP(){
        Random rand = new Random();
        this.stats.setLevelUp(1, 2 + rand.nextInt(2), 4 + rand.nextInt(2), 1 + rand.nextInt(2), 3 + rand.nextInt(2));
        evolve();
        learnMove();

    }

    @Override
    public void evolve() {
        if (this.stats.getLevel() == 35) {

            this.image = R.drawable.isooranssi;
            this.lutemon = this.evolution;
            this.description = "Tsembalo, tulilintu-Lutemon. Tsembalo on kehittynyt Undulasta. Kun Tsembalo on vihainen, se roihahtaa liekkeihin.";
            this.evolution = "-";
            this.stats.setLevelUp(0, 56, 170, 90, 230);
        }
    }

    @Override
    public void learnMove() {
        if (this.stats.getLevel() == 7) {
            moves.set(1, new AttackMove("Kipinä", 40));
        }if (this.stats.getLevel() == 12) {
            moves.set(3, new StatsImprover("Tuima tuijotus", 1.6, 1));
        }if (this.stats.getLevel() == 35) {
            moves.set(0, new AttackMove("Ilmaisku", 90));
        }if (this.stats.getLevel() == 55) {
            moves.set(2, new StatsImprover("Fenix-tuhkanousu", 2.5, 2));
        }if (this.stats.getLevel() == 70) {
            moves.set(1, new AttackMove("Fenix-liekkisyöksy", 180));
        }
    }


}
