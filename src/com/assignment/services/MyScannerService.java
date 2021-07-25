package com.assignment.services;

import java.util.Scanner;

public class MyScannerService {
    private  static  final Scanner _scanner = new Scanner(System.in);

    MyScannerService(){}

    public static Scanner getScannerInstance(){
        return _scanner;
    }
}
