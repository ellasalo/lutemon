package com.olio.lutemon.LutemonClasses;

import java.io.Serializable;

public class Statistics implements Serializable {
    int practiseMatches;
    int arenaMatches;
    int tournamentVictories;

    public Statistics(int practiseMatches, int arenaMatches, int tournamentVictories) {
        this.practiseMatches = practiseMatches;
        this.arenaMatches = arenaMatches;
        this.tournamentVictories = tournamentVictories;
    }

    public int getPractiseMatches() {
        return practiseMatches;
    }

    public int getArenaMatches() {
        return arenaMatches;
    }

    public int getTournamentVictories() {
        return tournamentVictories;
    }

    public void addArenaMatches() {
        this.arenaMatches = this.arenaMatches + 1;
    }

    public void addTournamentVictories() {
        this.tournamentVictories = this.tournamentVictories + 1;
    }

    public void addPractiseMatches() {
        this.practiseMatches = this.practiseMatches + 1;
    }


}
