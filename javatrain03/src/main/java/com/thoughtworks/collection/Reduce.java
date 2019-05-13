package com.thoughtworks.collection;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Reduce {

    List<Integer> arrayList;

    public Reduce(List<Integer> arrayList) {
        this.arrayList = arrayList;
    }

    public int getMaximum() {
        int max=0;
        for(Integer i:arrayList){
            if(i>max)max=i;
        }
        return max;
        //throw new NotImplementedException();
    }

    public double getMinimum() {
        int min=065535;
        for(Integer i:arrayList){
            if(i<min)min=i;
        }
        return min;
        //throw new NotImplementedException();
    }

    public double getAverage() {
        double sum=0;
        for(Integer i:arrayList){
            sum+=i;
        }
        double result=sum/arrayList.size();
        return result;
        //throw new NotImplementedException();
    }

    public double getOrderedMedian() {
        Collections.sort(arrayList);
        double j;
        if(arrayList.size()%2==0){
            j=(double)(arrayList.get(arrayList.size()/2-1)+arrayList.get(arrayList.size()/2))/2;
        }else{
            j=(double)arrayList.get(arrayList.size()/2) ;
        }
        Double result=new Double(j);
        return result;
        //throw new NotImplementedException();
    }

    public int getFirstEven() {
        int result=0;
        for(Integer i:arrayList){
            if(i%2==0){
                result=i;
                break;
            }
        }
        return result;
        //throw new NotImplementedException();
    }

    public int getIndexOfFirstEven() {
        int result=0;
        for(Integer i:arrayList){
            if(i%2==0){
                result=i;
                break;
            }
        }
        Integer index=new Integer(result);
        return arrayList.indexOf(index);
        //throw new NotImplementedException();
    }

    public boolean isEqual(List<Integer> arrayList) {
        boolean result=this.arrayList.containsAll(arrayList) && arrayList.containsAll(this.arrayList);
        return result;
        //throw new NotImplementedException();
    }

    //实现接口SingleLink，然后再此函数内使用
    public Double getMedianInLinkList(SingleLink singleLink) {
        //singleLink=new LinkSingle<Integer>();
        int size=0;
        for(int i:arrayList){
            singleLink.addTailPointer(i);
            size++;
        }
        double medium=0;
        if(size%2==0){
            int up=size/2,down=size/2+1;
            int fr=(Integer)singleLink.getNode(up);
            int ls=(Integer)singleLink.getNode(down);
            medium=(double)(fr+ls)/2;

        }
        else{
            Integer ls=(Integer)singleLink.getNode(size/2);
            medium=ls.intValue();

        }
        //medium=size;
        //return new Double(medium);
        return new Double(medium);
        //throw new NotImplementedException();
    }

    public int getLastOdd() {
        int result=0;
        for(Integer i:arrayList){
            if(i%2==1){
                result=i;
            }
        }
        return result;
        //throw new NotImplementedException();
    }

    public int getIndexOfLastOdd() {
        int result=0;
        for(Integer i:arrayList){
            if(i%2==1){
                result=i;
            }
        }
        Integer index=new Integer(result);
        return arrayList.indexOf(index);
        //throw new NotImplementedException();
    }
}
