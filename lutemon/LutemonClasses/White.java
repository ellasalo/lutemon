package com.olio.lutemon.LutemonClasses;
import com.olio.lutemon.R;

import java.util.ArrayList;
import java.util.Random;

public class White extends Lutemon{
    public White(String nickname) {
        super(R.drawable.pienivalkoinen, "Hippomon", nickname,"Valkoinen", "Musta", "Hippomon on hippo-Lutemon. Hippomon on luonteeltaan leikkisä. Hippomonit tykkäävät kieriä ruohikossa. Kun Hippomon saa ruokaa, sen korvat alkavat heilua.", "Hipponator", new Stats(1, 30, 1, 8, 2), new ArrayList<>(), new Statistics(0,0,0));
        moves.add(new AttackMove("Pure", 12));
        moves.add(new AttackMove("Töni", 7));
        moves.add(new StatsImprover("Väistele", 1, 1.2));
        moves.add(new StatsImprover("Ryhdistäydy", 1.3, 1));
    }

    @Override
    public void levelUP(){
        Random rand = new Random();
        this.stats.setLevelUp(1, 8 + rand.nextInt(2), 0 + rand.nextInt(2), 2 + rand.nextInt(2), 0 + rand.nextInt(2));
        evolve();
        learnMove();
    }

    @Override
    public void evolve() {
        if (this.stats.getLevel() == 35) {
            if (this.nickname.equals("Hippomon")) {
                this.nickname = "Hipponator";
            }
            this.image = R.drawable.isovalkoinen;
            this.lutemon = this.evolution;
            this.description = "Hipponator on Hippomonin kehittynyt muoto. Hipponator on raskain tunnettu olento. Se voi painaa jopa 2 miljoonaa Newtonia.";
            this.evolution = "-";
            this.stats.setLevelUp(0, 400, 60, 150, 50);
        }
    }

    @Override
    public void learnMove() {
        if (this.stats.getLevel() == 6) {
            moves.set(0, new StatsImprover("Pullistaudu", 1, 1.6));
        }if (this.stats.getLevel() == 17) {
            moves.set(2, new StatsImprover("Kasva", 1, 1.9));
        }if (this.stats.getLevel() == 35) {
            moves.set(1, new AttackMove("Istu päälle", 60));
        }if (this.stats.getLevel() == 52) {
            moves.set(0, new StatsImprover("Kovetu", 1, 2.5));
        }
    }

}
