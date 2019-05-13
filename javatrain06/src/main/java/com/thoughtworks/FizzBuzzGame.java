package com.thoughtworks;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;


public class FizzBuzzGame {
    private List<String> results = new ArrayList<>();
    private InfoChecker checker = new InfoChecker();
    private List<Integer> magicNumber;

    public FizzBuzzGame() {
        Integer[] magic={3,5,7};
        magicNumber=Arrays.asList(magic);
    }

    public void start(int count) {
        for (int i = 1; i <= count; i++) {
            results.add(replace(i));
        }
    }

    public void init() throws Exception {
        System.out.println("请输入学生个数：");
        start(Integer.parseInt(checker.read()));
        getResults().stream().forEach(item -> System.out.println(item));
    }

    public List<String> getResults() {
        return results;
    }

    public String replace(int i) {
        String result = "";
        result=replaceMagicNumber(i);
        if(checker.checkifNumber(result)){
            result=replaceMultipleNumber(i);
        }
        return result;
    }

    public String replaceMagicNumber(int i){
        String result = "";
        if (Integer.toString(i).contains(Integer.toString(magicNumber.get(0)))) {
            return "Fizz";
        }
        else {
            result = Integer.toString(i);
            return result;
        }
    }

    public String replaceMultipleNumber(int i){
        String result = "";
        if (i == magicNumber.get(0) || i % magicNumber.get(0) == 0) {
            result += "Fizz";
        }
        if (i == magicNumber.get(1) || i % magicNumber.get(1) == 0) {
            result += "Buzz";
        }
        if (i == magicNumber.get(2) || i % magicNumber.get(2) == 0) {
            result += "Whizz";
        }
        if (result == "") {
            result = Integer.toString(i);
        }
        return result;
    }
}

