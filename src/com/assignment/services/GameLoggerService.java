package com.assignment.services;

import com.assignment.constants.ANSIColors;

public class GameLoggerService {

    public static void info(String message){
        System.out.println(ANSIColors.BLUE.concat(message).concat(ANSIColors.RESET));
    }
    public static void success(String message){
        System.out.println(ANSIColors.GREEN.concat(message).concat(ANSIColors.RESET));
    }
    public static void error(String message){
        System.out.println(ANSIColors.RED.concat(message).concat(ANSIColors.RESET));
    }

    public static void important(String message){
        System.out.println(ANSIColors.YELLOW.concat(message).concat(ANSIColors.RESET));
    }

}
