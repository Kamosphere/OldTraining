package com.tw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class student {
    private String name;
    private Integer id;
    private Map<String,Integer> score;

    private Double average;
    private Integer sum;

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    student() {
        name = "";
        id = -1;
        score=new HashMap<>();
        average=0.0;
        sum=0;
    }

    public String addScore(String addline){//姓名、学号、科目
        String noblank=addline.replace(" ", "");
        String[] parts=noblank.split(",");
        String[] temp;
        this.setName(parts[0]);
        this.setId(Integer.valueOf(parts[1]));
        for(int i=2;i<parts.length;i++){
            temp=parts[i].split(":");
            if(score.containsKey(temp[0])){
                score.remove(temp[0]);
            }
            score.put(temp[0],Integer.valueOf(temp[1]));
            this.setSum(this.getSum()+Integer.valueOf(temp[1]));
        }
        double tempave=this.getSum().doubleValue();
        this.setAverage( (tempave/score.size()));
        return this.getName();
    }

    public Integer getScore(String subject) {
            for (String s : score.keySet()) {
                if(s.equals(subject)){
                    return score.get(s);
                }
            }
        return new Integer(0);
    }
}