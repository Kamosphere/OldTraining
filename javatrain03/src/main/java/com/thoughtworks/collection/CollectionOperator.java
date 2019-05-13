package com.thoughtworks.collection;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.*;

public class CollectionOperator {
    public List<Integer> getListByInterval(int left, int right) {
        List<Integer> result=new ArrayList<Integer>();
        if(left>right){
            for(int i=left;i>=right;i--){
                result.add(new Integer(i));
            }
        }
        else{
            for(int i=left;i<=right;i++){
                result.add(new Integer(i));
            }
        }
        return result;
        //throw new NotImplementedException();
    }

    public List<Integer> getEvenListByIntervals(int left, int right) {
        List<Integer> result=new ArrayList<Integer>();
        if(left>right){
            for(int i=left;i>=right;i--){
                if(i%2==0) result.add(new Integer(i));
            }
        }
        else{
            for(int i=left;i<=right;i++){
                if(i%2==0) result.add(new Integer(i));
            }
        }
        return result;
        //throw new NotImplementedException();
    }

    public List<Integer> popEvenElments(int[] array) {
        List<Integer> result=new ArrayList<Integer>();
        for(Integer i:array){
            if(i%2==0){
                result.add(i);
            }
        }
        return result;
        //throw new NotImplementedException();
    }

    public int popLastElment(int[] array) {
        int last=array.length-1;
        if(last>0)return array[last];
        else return -1;
        //throw new NotImplementedException();
    }

    public List<Integer> popCommonElement(int[] firstArray, int[] secondArray) {
        List<Integer> firstList =Arrays.stream(firstArray).boxed().collect(Collectors.toList());
        List<Integer> secondList = Arrays.stream(secondArray).boxed().collect(Collectors.toList());
        List<Integer> result=new ArrayList<Integer>();
        for(Integer i:firstList){
            if(secondList.contains(i)){
                result.add(i);
            }
        }
        return result;
        //throw new NotImplementedException();
    }

    public List<Integer> addUncommonElement(Integer[] firstArray, Integer[] secondArray) {
        List<Integer> firstList = Arrays.asList(firstArray);
        List<Integer> secondList = Arrays.asList(secondArray);
        List<Integer> result=new ArrayList<Integer>();
        result.addAll(firstList);
        for(Integer i:secondList){
            if(!firstList.contains(i)){
                result.add(i);
            }
        }
        return result;
        //throw new NotImplementedException();
    }
}
