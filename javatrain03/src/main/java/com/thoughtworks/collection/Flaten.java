package com.thoughtworks.collection;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Flaten {

    Integer[][] array;
    public Flaten(Integer[][] array) {
        this.array = array;
    }

    public List<Integer> transformToOneDimesional() {
        List<Integer> result=new ArrayList<Integer>();
        int index = 0;
        for (Integer[] i : array) {
            for (Integer element : i) {
                result.add(element);
            }
        }
        return result;
        //throw new NotImplementedException();
    }

    public List<Integer> transformToUnrepeatedOneDimesional() {
        List<Integer> result=new ArrayList<Integer>();
        int index = 0;
        for (Integer[] i : array) {
            for (Integer element : i) {
                if(!result.contains(element)) result.add(element);
            }
        }
        return result;
        //throw new NotImplementedException();
    }
}
