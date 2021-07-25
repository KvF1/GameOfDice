package com.assignment.models;

import com.assignment.constants.Labels;
import com.assignment.interfaces.TableRecord;

import java.util.HashMap;

public class Player implements TableRecord {
    private int score = 0;
    private int rank = 0;
    private String playerName;
    private int lastRoll = 0;
    private boolean canPlayNextRound = true;

    public Player(String playerNumber){
        this.playerName = Labels.PLAYER_NAME_PREFIX.concat(playerNumber);
    }

    public int getScore() {
        return score;
    }

    public void incrementScore(int increment) {
        this.score = this.score + increment;
    }

    public String getPlayerName() {
        return playerName;
    }


    public void setLastRoll(int lastRoll) {
        this.lastRoll = lastRoll;
    }

    public int getLastRoll() {
        return this.lastRoll;
    }

    public boolean getCanPlayNextRound() {
        return canPlayNextRound;
    }

    public void setCanPlayNextRound(boolean canPlayRound) {
        this.canPlayNextRound = canPlayRound;
    }

    public HashMap<String, String> getTableRecords(){
        HashMap<String, String> tableRecords = new HashMap<String, String>();
        tableRecords.put("playerRank", Integer.toString(this.getRank()));
        tableRecords.put("playerName", this.getPlayerName());
        tableRecords.put("playerScore", Integer.toString( this.getScore()));
        return tableRecords;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
