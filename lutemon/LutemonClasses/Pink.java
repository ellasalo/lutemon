package com.olio.lutemon.LutemonClasses;

import com.olio.lutemon.R;

import java.util.ArrayList;
import java.util.Random;

public class Pink extends Lutemon{
    public Pink(String nickname) {
        super(R.drawable.pienipinkki, "Mörkyli", nickname,"Pinkki", "Vihreä", "Mörkyli on karvapallero-Lutemon. Mörkyli on tunnettu sen telepaattisita kyvyistä. Mörkylit asuvat luolissa yhteiskuntana.", "Mörrimöykky", new Stats(1, 18, 6, 5, 6), new ArrayList<>(), new Statistics(0,0,0));
        moves.add(new AttackMove("Pure", 12));
        moves.add(new AttackMove("Töni", 7));
        moves.add(new StatsImprover("Väistele", 1, 1.2));
        moves.add(new StatsImprover("Ryhdistäydy", 1.3, 1));
    }

    public Pink(int image, String lutemon, String nickname, String type, String weakness, String description, String evolution, Stats stats, ArrayList<Move> moves, Statistics statistics) {
        super(image, lutemon, nickname, type, weakness, description, evolution, stats, moves, statistics);
    }

    @Override
    public void levelUP(){
        Random rand = new Random();
        this.stats.setLevelUp(1, 3 + rand.nextInt(2), 2 + rand.nextInt(2), 2 + rand.nextInt(2), 1 + rand.nextInt(2));
        evolve();
        learnMove();
    }

    @Override
    public void evolve() {
        if (this.stats.getLevel() == 35) {
            if (this.nickname.equals("Mörkyli")) {
                this.nickname = "Mörrimöykky";
            }
            this.image = R.drawable.isopinkki;
            this.lutemon = this.evolution;
            this.description = "Mörrimöykky, psyyke-Lutemon. Mörrimöykky on kehittynyt Mörkylistä. Toisin kuin Mörkyli, Mörrimöykky tarvitsee omaa rauhaa. Mörrimöykkyjä tavataan yleensä vaikeakulkuisissa vuoristoissa, joissa ne saavat olla rauhassa.";
            this.evolution = "-";
            this.stats.setLevelUp(0, 125, 50, 100, 60);
        }
    }

    @Override
    public void learnMove() {
        if (this.stats.getLevel() == 6) {
            moves.set(1, new AttackMove("Aivoimpulssi", 40));
        }if (this.stats.getLevel() == 17) {
            moves.set(3, new StatsImprover("Telepatia", 2, 1));
        }if (this.stats.getLevel() == 35) {
            moves.set(0, new AttackMove("Laser", 120));
        }if (this.stats.getLevel() == 52) {
            moves.set(2, new StatsImprover("Psyykkaus", 1.5, 1.5));
        }
    }

}
