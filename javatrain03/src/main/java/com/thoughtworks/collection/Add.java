package com.thoughtworks.collection;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Add {
    public int getSumOfEvens(int leftBorder, int rightBorder) {
        int sum=0;
        if(leftBorder<rightBorder){
            for(int i=leftBorder;i<=rightBorder;i++){
                if(i%2==0)sum=sum+i;
            }
        }
        else{
            for(int i=leftBorder;i>=rightBorder;i--){
                if(i%2==0)sum=sum+i;
            }
        }
        return sum;
        //throw new NotImplementedException();
    }

    public int getSumOfOdds(int leftBorder, int rightBorder) {
        int sum=0;
        if(leftBorder<rightBorder){
            for(int i=leftBorder;i<=rightBorder;i++){
                if(i%2==1)sum=sum+i;
            }
        }
        else{
            for(int i=leftBorder;i>=rightBorder;i--){
                if(i%2==1)sum=sum+i;
            }
        }
        return sum;
        //throw new NotImplementedException();
    }

    public int getSumTripleAndAddTwo(List<Integer> arrayList) {
        int sum=0;
        for(Integer i:arrayList){
            sum=sum+(3*i.intValue()+2);
        }
        return sum;
        //throw new NotImplementedException();
    }

    public int getSumOfProcessedOdds(List<Integer> arrayList) {
        int sum=0;
        for(Integer i:arrayList){
            if(i.intValue()%2==1) sum=sum+(3*i.intValue()+5);
        }
        return sum;
        //throw new NotImplementedException();
    }

    public List<Integer> getTripleOfOddAndAddTwo(List<Integer> arrayList) {
        List<Integer> result=new ArrayList<Integer>();
        for(Integer i:arrayList){
            int temp=0;
            if(i.intValue()%2==1){
                temp=3*i.intValue()+2;
            }
            else{
                temp=i.intValue();
            }
            result.add(new Integer(temp));
            temp=0;
        }
        return result;
        //throw new NotImplementedException();
    }

    public double getMedianOfEvenIndex(List<Integer> arrayList) {
        List<Integer> temparr=new ArrayList<Integer>();
        for(Integer i:arrayList){
            if(i.intValue()%2==0){
                temparr.add(i);
            }
        }
        Collections.sort(temparr);
        double j;
        if(temparr.size()%2==0){
            j=(double)(temparr.get(temparr.size()/2-1)+temparr.get(temparr.size()/2))/2;
        }else{
            j=(double)temparr.get(temparr.size()/2) ;
        }
        Double result=new Double(j);
        return result;
       //throw new NotImplementedException();
    }

    public double getAverageOfEvenIndex(List<Integer> arrayList) {
        int sum=0,count=0;
        for(Integer i:arrayList){
            if(i.intValue()%2==0) {
                sum=sum+(i.intValue());
                count++;
            }
        }
        double avg=sum/count;
        return avg;
        //throw new NotImplementedException();
    }

    public boolean isIncludedInEvenIndex(List<Integer> arrayList, Integer specialElment) {
        List<Integer> temparr=new ArrayList<Integer>();
        for(Integer i:arrayList){
            if(i.intValue()%2==0){
                temparr.add(i);
            }
        }
        if(temparr.contains(specialElment)){
            return true;
        }
        else return false;
        //throw new NotImplementedException();
    }

    public List<Integer> getUnrepeatedFromEvenIndex(List<Integer> arrayList) {
        List<Integer> temparr=new ArrayList<Integer>();
        for(Integer i:arrayList){
            if((i.intValue()%2==0)&&(!temparr.contains(i))){
                temparr.add(i);
            }
        }
        return temparr;
        //throw new NotImplementedException();
    }

    public List<Integer> sortByEvenAndOdd(List<Integer> arrayList) {
        List<Integer> oddarr=new ArrayList<Integer>();
        List<Integer> evenarr=new ArrayList<Integer>();
        for(Integer i:arrayList){
            if(i.intValue()%2==0){
                evenarr.add(i);
            }
            else{
                oddarr.add(i);
            }
        }
        Collections.sort(evenarr);
        Collections.sort(oddarr,Collections.reverseOrder());
        List<Integer> result=new ArrayList<Integer>();
        result.addAll(evenarr);
        result.addAll(oddarr);
        return result;
        //throw new NotImplementedException();
    }

    public List<Integer> getProcessedList(List<Integer> arrayList) {
        List<Integer> result = new ArrayList<Integer>();
        if(arrayList.size()<1){
            result.add(arrayList.get(0));
        }
        else {
            for(int i=1;i<arrayList.size();i++){
                int addUnit=arrayList.get(i-1).intValue()+arrayList.get(i).intValue();
                Integer addInteger=new Integer(3*addUnit);
                result.add(addInteger);
            }
        }
        return result;
        //throw new NotImplementedException();
    }
}
