package com.assignment.constants;

public final class Messages {
    public  static  final  String INVALID_ARGUMENTS = "Invalid input. Please enter argument 1 player number (min value 2) and argument 2 winning total (min value 1) of type Integer";
    public  static  final  String INTRODUCTION_MESSAGE = "Welcome to the Game of Dice. Get %d to win.\n";
    public  static  final  String HOW_TO_PLAY = "Press r to roll the dice";
    public  static  final  String INVALID_INPUT = "Unknown Input. %s ".concat(HOW_TO_PLAY).concat(".");
    public  static  final  String ROLL_DICE_PROMPT = "%s it's your turn (".concat(HOW_TO_PLAY).concat(")");
    public  static  final  String ROLLED_DICE_VALUE = "%s rolled a %d \n";
    public  static  final  String PLAYER_PENALIZED = "%s had rolled %d twice so will have to skip this turn";
    public  static  final  String BONUS_ROUND = "%s had rolled a %d. Congrats !! You get another chance\n";
    public  static  final  String RANK_EARNED = "Congrats %s you have earned the rank %d in this Game of Dice \n";
    public  static  final  String GAME_WON = "Game over. Final Ranking's \n";

}
