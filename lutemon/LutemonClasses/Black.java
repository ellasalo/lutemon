package com.olio.lutemon.LutemonClasses;
import com.olio.lutemon.R;

import java.util.ArrayList;
import java.util.Random;

public class Black extends Lutemon{
    public Black(String nickname) {
        super(R.drawable.pienimusta, "El Diabolo", nickname,"Musta", "Pinkki", "El Diabolo on lohikäärme-Lutemon. Muinaisissa kertomuksissa El Diabolo on liitetty maailmanloppuun. Sen läheisyydessä ilma muuttuu raskaammaksi ja on vaikea hengittää.", "Estrella de Inferno", new Stats(1, 16, 10, 1, 10), new ArrayList<>(), new Statistics(0,0,0));
        moves.add(new AttackMove("Pure", 12));
        moves.add(new AttackMove("Töni", 7));
        moves.add(new StatsImprover("Väistele", 1, 1.2));
        moves.add(new StatsImprover("Ryhdistäydy", 1.3, 1));
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
            if (this.nickname.equals("El Diabolo")) {
                this.nickname = "Estrella de Inferno";
            }
            this.image = R.drawable.isomusta;
            this.lutemon = this.evolution;
            this.description = "Estrella de Inferno, enkeli-Lutemon. Estrella de Inferno on El Diabolon kehittynyt muoto. Estrella de Infernon ankean olemuksen ympärillä kasvit lakastuvat. Sitä esiintyy lähinnä öisin.";
            this.evolution = "-";
            this.stats.setLevelUp(0,86,200,70,150);
        }
    }

    @Override
    public void learnMove() {
        if (this.stats.getLevel() == 8) {
            moves.set(1, new AttackMove("Siipi-isku", 40));
        }if (this.stats.getLevel() == 17) {
            moves.set(3, new StatsImprover("Tuima tuijotus", 1.6, 1));
        }if (this.stats.getLevel() == 35) {
            moves.set(0, new AttackMove("Musta pulssi", 100));
        }if (this.stats.getLevel() == 52) {
            moves.set(3, new StatsImprover("Raivo", 3, 0.7));
        }if (this.stats.getLevel() == 60) {
            moves.set(1, new AttackMove("Pimeyssyöksykierre", 150));
        }
    }

}
