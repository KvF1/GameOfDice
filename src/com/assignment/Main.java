package com.assignment;

import com.assignment.constants.Messages;
import com.assignment.controllers.Game;
import com.assignment.services.GameLoggerService;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            GameLoggerService.error(Messages.INVALID_ARGUMENTS);
            return;
        }
        try{
            Game GameOne = new Game(args[0], args[1]);
            GameOne.start();
        }catch (Exception e){
            GameLoggerService.error(Messages.INVALID_ARGUMENTS);
            return;
        }
    }
}
