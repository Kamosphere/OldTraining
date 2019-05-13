package com.thoughtworks.collection;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Filter {

    List<Integer>  array;

    public Filter(List<Integer> array) {
     this.array = array;
    }

    public List<Integer> filterEven() {
        List<Integer> result=new ArrayList<Integer>();
        for(Integer i:array){
            if(i.intValue()%2==0) {
                result.add(i);
            }
        }
        return result;
        //throw new NotImplementedException();
    }

    public List<Integer> filterMultipleOfThree() {
        List<Integer> result=new ArrayList<Integer>();
        for(Integer i:array){
            if(i.intValue()%3==0) {
                result.add(i);
            }
        }
        return result;
        //throw new NotImplementedException();
    }

    public List<Integer> getCommonElements(List<Integer> firstList, List<Integer> secondList) {
        List<Integer> result=new ArrayList<Integer>();
        for(Integer i:firstList){
            if(secondList.contains(i)){
                result.add(i);
            }
        }
        return result;
        //throw new NotImplementedException();
    }

    public List<Integer> getDifferentElements() {
        List<Integer> result=new ArrayList<Integer>();
        for(Integer i:array){
            if(!result.contains(i)){
                result.add(i);
            }
        }
        return result;
        //throw new NotImplementedException();
    }
}