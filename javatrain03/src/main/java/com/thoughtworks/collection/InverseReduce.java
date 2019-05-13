package com.thoughtworks.collection;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InverseReduce {

    private Random random = new Random();

    public InverseReduce() {}

    public InverseReduce(Random random) {
        this.random = random;
    }

    public List<Integer> divideToSmaller(int number) {
        List<Integer> result=new ArrayList<Integer>();
        int step=random.nextInt(3);
        int numbers=number;
        while(numbers-step>0){
            numbers=numbers-step;
            result.add(new Integer(numbers));
        }
        return result;
        //throw new NotImplementedException();
    }
}
