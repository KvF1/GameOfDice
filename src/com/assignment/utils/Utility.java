package com.assignment.utils;

import com.assignment.models.Player;

public class Utility {
    public  static  String rightPadStringWithSpaces(String inputString, int length){
        return String.format("%-" + length + "s", inputString);
    }

    public static <T> void randomizeInputArray(T[] inputArr){
        int inputArrLength = inputArr.length;
        for (int i = 0; i < inputArrLength; i++){
            int randomIndex = (int)Math.random() * inputArrLength;
            T temp = inputArr[i];
            inputArr[i] = inputArr[randomIndex];
            inputArr[randomIndex] = temp;
        }
    }
}
