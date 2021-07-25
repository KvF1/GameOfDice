package com.assignment.controllers;

import com.assignment.constants.MagicNumbers;
import com.assignment.constants.Messages;
import com.assignment.models.Player;
import com.assignment.services.GameLoggerService;
import com.assignment.services.GameService;
import com.assignment.utils.Utility;

public class Game {

    private  Player[] playersList;
    private  int currentPlayerIndex = 0;
    private int pointsToWin = 0;
    private int rank = 1;

    public Game(String numberOfPlayers, String pointsToWin) throws Exception {
        int _numberOfPlayers;
        int _pointsToWin;
        try{
            _numberOfPlayers = Integer.parseInt(numberOfPlayers);
            _pointsToWin = Integer.parseInt(pointsToWin);
        }catch (Exception e){
            throw  new Exception(Messages.INVALID_ARGUMENTS);
        }
        boolean isInvalidInput = (_numberOfPlayers < MagicNumbers.MINIMUM_NO_OF_PLAYERS_PER_GAME ||
                _pointsToWin < MagicNumbers.MINIMUM_WINNING_POINTS_PER_GAME);

        if(isInvalidInput){
            throw  new Exception(Messages.INVALID_ARGUMENTS);
        }
        this.playersList = initPlayers(_numberOfPlayers);
        this.currentPlayerIndex = 0;
        this.pointsToWin = _pointsToWin;
    }

    private Player getCurrentPlayer(){
        return this.playersList[currentPlayerIndex];
    }

    private void incrementRank(){
        this.rank += 1;
    }

    private static Player[] initPlayers(int numberOfPlayers){
        Player[] players = new Player[numberOfPlayers];
        for(int i = 0; i < players.length; i++){
            players[i] = new Player(Integer.toString(i ));
        }
        Utility.randomizeInputArray(players);
        return players;
    }

    private int getNextValidPlayerIndex(int currentPlayerIndex){
        if(isGameOver()){
            return 0;
        }
        int i = (currentPlayerIndex + 1) % this.playersList.length;
        while(i < this.playersList.length){
            if(this.playersList[i].getRank() == 0){
                return i;
            }
            i = (i + 1) % this.playersList.length;
        }
        return i;
    }

    private void  initNextRound(Player currentPlayer){
        if(currentPlayer.getRank() != 0){
            this.currentPlayerIndex = this.getNextValidPlayerIndex(this.currentPlayerIndex);
            return;
        }
        if(currentPlayer.getLastRoll() == MagicNumbers.BONUS_ROUND_VALUE) {
            GameLoggerService.success(String.format(Messages.BONUS_ROUND,currentPlayer.getPlayerName(),  MagicNumbers.BONUS_ROUND_VALUE));
            return;
        }

        this.currentPlayerIndex = this.getNextValidPlayerIndex(this.currentPlayerIndex);

        if(! this.getCurrentPlayer().getCanPlayNextRound()){
            GameLoggerService.error(String.format(Messages.PLAYER_PENALIZED, this.getCurrentPlayer().getPlayerName(), MagicNumbers.PENALTY_DICE_VALUE));
            this.updatePlayerState(this.getCurrentPlayer(), 0);
            this.currentPlayerIndex = this.getNextValidPlayerIndex(this.currentPlayerIndex);
        }
    }

    private  void updatePlayerState(Player p, int playerRolledDiceValue){
        p.incrementScore(playerRolledDiceValue);
        if(p.getLastRoll() == MagicNumbers.PENALTY_DICE_VALUE &&
                playerRolledDiceValue == MagicNumbers.PENALTY_DICE_VALUE){
            p.setCanPlayNextRound(false);
            return;
        }
        p.setLastRoll(playerRolledDiceValue);
        p.setCanPlayNextRound(true);
    }

    private boolean hasPlayerWon(Player p){
        return (p.getScore() >= this.pointsToWin);
    }

    private  boolean isGameOver(){
        for (Player p : this.playersList){
            if(p.getRank() == 0){
                return false;
            }
        }
        return true;
    }


    public void start(){
        GameLoggerService.success(String.format(Messages.INTRODUCTION_MESSAGE, this.pointsToWin));
        while (! this.isGameOver()){
            String playerInput = GameService.getPlayerInput(this.getCurrentPlayer());
            if(playerInput.equals("r")){
                int diceValue = GameService.rollDice(this.getCurrentPlayer());
                this.updatePlayerState(this.getCurrentPlayer(), diceValue);

                if(hasPlayerWon(this.getCurrentPlayer())){
                    this.getCurrentPlayer().setRank(this.rank);
                    GameLoggerService.success(String.format(Messages.RANK_EARNED, this.getCurrentPlayer().getPlayerName(), this.getCurrentPlayer().getRank()));
                    this.incrementRank();
                }
                if(this.isGameOver()){
                    break;
                }

                GameService.printRankBoard(this.playersList);
                this.initNextRound(this.getCurrentPlayer());
            }
        }
        GameLoggerService.info(Messages.GAME_WON);
        GameService.printRankBoard(this.playersList);
    }
}
