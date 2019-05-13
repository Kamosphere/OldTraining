package com.thoughtworks;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InfoChecker {
    private Scanner scanner = new Scanner(System.in);
    private Pattern pattern = Pattern.compile("-?[0-9]*");

    public boolean checkifNumber(String input){
        if (pattern.matcher(input).matches() && Integer.parseInt(input) > 0) {
            return true;
        }
        else return false;
    }

    public String read() throws Exception {
        String input = scanner.next();
        if (checkifNumber(input)) {
            return input;
        }
        throw new Exception("Invalid Input");
    }

}