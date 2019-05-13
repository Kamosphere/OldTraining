package com.tw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Library {

    private inputChecker sc;
    private List<student> stulist=new ArrayList<student>();
    private List<String> subject=new ArrayList<>();
    private Double classSumMedium=new Double(0);
    private Double classSumAverage=new Double(0);

    public Library(inputChecker scs) {
        this.sc=scs;
    }

    public Library() {
    }

    public Double getClassSumAverage() {
        return classSumAverage;
    }

    public void setClassSumAverage(Double classSumAverage) {
        this.classSumAverage = classSumAverage;
    }

    public Double getClassSumMedium() {
        return classSumMedium;
    }

    public void setClassSumMedium(Double classSumMedium) {
        this.classSumMedium = classSumMedium;
    }

    public student searchStudent(Integer id){
        for(student stu:stulist){
            if(stu.getId().equals(id)){
                return stu;
            }
        }
        return null;
    }

    public void updateClassSum(){
        List<Integer> mediumClass=new ArrayList<>();
        Double averageClass = new Double(0);
        for(student stu:stulist){
            mediumClass.add(stu.getSum());
            averageClass=averageClass+stu.getSum();
        }
        Collections.sort(mediumClass);
        int size=mediumClass.size();
        if(size==1){
            this.setClassSumMedium(mediumClass.get(0).doubleValue());
        }
        else if(size%2==1){
            this.setClassSumMedium(mediumClass.get(size%2).doubleValue());
        }
        else if(size%2==0){
            Integer first=mediumClass.get(size/2-1);
            Integer last=mediumClass.get(size/2);
            double medium=(double)(first+last)/2;
            this.setClassSumMedium(new Double(medium));
        }
        averageClass=averageClass/size;
        this.setClassSumAverage(averageClass);
    }

    public void addSubject(String addline){
        String noblank=addline.replace(" ", "");
        String[] parts=noblank.split(",");
        String[] temp;
        for(int i=2;i<parts.length;i++){
            temp=parts[i].split(":");
            if(!subject.contains(temp[0])){
                subject.add(temp[0]);
            }
        }
    }
    //--------------structure--------------//

    public void mainMenu() throws Exception{
        while (mainMessage()) {}
    }

    public boolean mainMessage() throws Exception{
        System.out.print("1. 添加学生\n" +
                "2. 生成成绩单\n" +
                "3. 退出\n" +
                "请输入你的选择（1～3）：");
        switch(Integer.parseInt(sc.read(2))){
            case 1:{
                addStudent();
                return true;
            }
            case 2:{
                generateScoreList();
                return true;
            }
            case 3:{
                return false;
            }
            default:{
                return false;
            }
        }
        //sc.close();
    }

    public void addStudent() throws Exception{
        String name,message;
        student newstu=new student();
        System.out.print("请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：\n");
        while(true){
            message=sc.read(0);
            if(message == "invalid"){
                System.out.print("请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）：\n");
            }
            else{
                String noblank=message.replace(" ","");
                String[] parts=noblank.split(",");
                for(student st:stulist){
                    if(st.getId().equals(Integer.valueOf(parts[1]))){
                        stulist.remove(st);
                        break;
                    }
                }
                addSubject(message);
                name=newstu.addScore(message);
                stulist.add(newstu);
                updateClassSum();
                System.out.print("学生"+name+"的成绩被添加\n");
                break;
            }
        }
        //sc.close();
    }

    public void generateScoreList() throws Exception {
        List<String> stulist;
        String message;
        System.out.print("请输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：");
        while(true){
            message=sc.read(1);
            if(message=="invalid"){
                System.out.print("请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：");
            }
            else{
                String noblank=message.replace(" ", "");
                String[] idList=noblank.split(",");
                stulist=Arrays.asList(idList);
                System.out.print("成绩单\n" );
                String subjectTitle="姓名";
                for(String sub:subject){
                    subjectTitle=subjectTitle+"|"+sub;
                }
                subjectTitle=subjectTitle+"|平均分|总分\n";
                System.out.print(subjectTitle+
                        "========================\n" );
                for(String stid:stulist){
                    student stunit=searchStudent(Integer.valueOf(stid));
                    if(stunit!=null){
                        String output=stunit.getName();
                        for(String suj:subject){
                            output=output+"|"+stunit.getScore(suj);
                        }
                        output=output+"|"+stunit.getAverage()+"|"+stunit.getSum()+"\n";
                        System.out.print(output);
                    }
                }
                System.out.print("========================\n" +
                        "全班总分平均数："+this.getClassSumAverage()+"\n" +
                        "全班总分中位数："+this.getClassSumMedium()+"\n");
                break;
            }
        }
    }

}