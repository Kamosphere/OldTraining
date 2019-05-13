package com.thoughtworks.collection;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MyMap {

    List<Integer> array;
    private String[] letters = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
            "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private List<String> letterList = Arrays.asList(letters);

    public MyMap(List<Integer> array) {
        this.array = array;
    }

    public List<Integer> getTriple() {
        List<Integer> result=new ArrayList<Integer>();
        for(Integer i:array){
            result.add(3*i.intValue());
        }
        return result;
        //throw new NotImplementedException();
    }

    public List<String> mapLetter() {
        List<String> result=new ArrayList<String>();
        for(Integer i:array){
            result.add(letters[array.indexOf(i)]);
        }
        return result;
        //throw new NotImplementedException();
    }

    public List<String> mapLetters() {
        List<String> result=new ArrayList<String>();
        int Index=1;
        for(Integer i:array){
            if(i.intValue()<27){
                int c=i-1;
                result.add(letterList.get(c));
            }
            else if(i.intValue()>=27){
                Index=i%26-1;
                int c=i/26-1;
                if(Index<0){
                    c--;
                    Index=Index+26;
                }
                String temp1 = letterList.get(Index);
                String temp2 = letterList.get(c);
                temp2=temp2+temp1;
                result.add(temp2);
            }
        }
        return result;
        //throw new NotImplementedException();
    }

    public List<Integer> sortFromBig() {
        List<Integer> result=new ArrayList<Integer>();
        result.addAll(array);
        Collections.sort(result,Collections.reverseOrder());
        return result;
        //throw new NotImplementedException();
    }

    public List<Integer> sortFromSmall() {
        List<Integer> result=new ArrayList<Integer>();
        result.addAll(array);
        Collections.sort(result);
        return result;
        //throw new NotImplementedException();
    }
}
