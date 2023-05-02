package com.olio.lutemon.LutemonClasses;
import com.olio.lutemon.R;

import java.util.ArrayList;
import java.util.Random;

public class Green extends Lutemon{
    public Green(String nickname) {
        super(R.drawable.pienivihrea, "Lutenatus Vexteus", nickname,"Vihreä", "Oranssi", "Lutenatus Vexteus on Mario Kart kasvi-Lutemon. Lutenatus Vexteus elää Mario Kart peleissä. Se tykkää asua ruukuissa ja hyökätä ohikiitävien kilpa-ajajien kimppuun.", "Lutenatus Vexteus Maximus", new Stats(1, 16, 6, 3, 8), new ArrayList<>(), new Statistics(0,0,0));
        moves.add(new AttackMove("Pure", 12));
        moves.add(new AttackMove("Töni", 7));
        moves.add(new StatsImprover("Väistele", 1, 1.2));
        moves.add(new StatsImprover("Ryhdistäydy", 1.3, 1));
    }

    @Override
    public void levelUP(){
        Random rand = new Random();
        this.stats.setLevelUp(1, 3+rand.nextInt(2), 2+rand.nextInt(2), 2+rand.nextInt(2), 2+rand.nextInt(2));
        evolve();
        learnMove();
    }

    @Override
    public void evolve() {
        if (this.stats.getLevel() == 35) {
            if (this.nickname.equals("Lutenatus Vexteus")) {
                this.nickname = "Lutenatus Vexteus Maximus";
            }
            this.image = R.drawable.isovihrea;
            this.lutemon = this.evolution;
            this.description = "Lutenatus Vexteus Maximus on lonkerokasvi-Lutemon. Lutenatus Vexteus Maximus on kehittynyt Lutenatus Vexteuksesta. Lutenatus Vexteus Maximus kykenee liikkumaan lonkeroillaan. Sen kieli erittää voimakasta myrkkyä, joka iholle osuessa lamaannuttaa.";
            this.evolution = "-";
            this.stats.setLevelUp(0, 100, 80, 50, 80);
        }
    }

    @Override
    public void learnMove() {
        if (this.stats.getLevel() == 6) {
            moves.set(0, new AttackMove("Puraisu", 40));
        }if (this.stats.getLevel() == 17) {
            moves.set(2, new StatsImprover("Yhteytys", 1, 1.6));
        }if (this.stats.getLevel() == 35) {
            moves.set(1, new AttackMove("Myrkkykieli", 110));
        }if (this.stats.getLevel() == 52) {
            moves.set(3, new StatsImprover("Lehtitanssi", 2.2, 1));
        }
    }


}
