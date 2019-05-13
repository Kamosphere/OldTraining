package com.tw;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class inputChecker{
    private Scanner scanner = new Scanner(System.in);

    public String read(int flag) throws Exception {
        scanner.useDelimiter("\n");
        String inputString = scanner.next();
        return validate(inputString, flag) ? inputString : "invalid";
    }

    public boolean validate(String inputString, int flag) {
        String[] arr = inputString.split(", ");
        Pattern pattern = Pattern.compile("-?[0-9]*");
        if (flag == 0) {
            if (arr.length < 3 ||!pattern.matcher(arr[1]).matches() ) {
                return false;
            }
            for (int i = 0; i < arr.length; i++) {
                if (i > 1) {
                    if (!arr[i].contains(": ") || !pattern.matcher(arr[i].split(": ")[1]).matches()) {
                        return false;
                    }
                }
            }
        } else if (flag == 1) {
            return Arrays.asList(arr).stream().allMatch(item -> pattern.matcher(item).matches());
        } else {
            return pattern.matcher(inputString).matches();
        }

        return true;
    }
}