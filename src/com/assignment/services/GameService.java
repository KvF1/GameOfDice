package com.assignment.services;

import com.assignment.constants.Labels;
import com.assignment.constants.LeaderboardConfig;
import com.assignment.constants.MagicNumbers;
import com.assignment.constants.Messages;
import com.assignment.models.Player;

import java.util.Arrays;
import java.util.Scanner;

public class GameService {

    public static void printRankBoard(Player[] playersList){
        Player[] leaderBoard = playersList.clone();
        Arrays.sort(leaderBoard, (a, b) -> {
            if(a.getRank() == 0 && b.getRank() == 0) {
                return  0;
            }
            if(a.getRank() == 0){
                return 1;
            }
            if(b.getRank() == 0){
                return  -1;
            }
            return a.getRank() - b.getRank();
        });
        StringBuilder rankBoard = new StringBuilder();
        rankBoard.append(Labels.RANK_TABLE).append("\n");
        rankBoard.append(TextTableService.create(leaderBoard, LeaderboardConfig.columns));
        GameLoggerService.important(rankBoard.toString());
    }

    public static int rollDice(Player currentPlayer){
        int diceValue = (int) (Math.random() * MagicNumbers.FACES_IN_DICE) + 1;
        GameLoggerService.success(String.format(Messages.ROLLED_DICE_VALUE, currentPlayer.getPlayerName(), diceValue));
        return diceValue;
    }

    public static String getPlayerInput(Player currentPlayer){
        GameLoggerService.info(String.format(Messages.ROLL_DICE_PROMPT, currentPlayer.getPlayerName()));
        Scanner myScanner = MyScannerService.getScannerInstance();
        String playerInput = myScanner.nextLine();
        while (! playerInput.equals("r")){
            GameLoggerService.error(String.format(Messages.INVALID_INPUT, currentPlayer.getPlayerName()));
            playerInput = myScanner.nextLine();
        }
        return playerInput;
    }
}
