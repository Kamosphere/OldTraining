package cn.school.thoughtworks.section3;

import java.util.List;
import java.util.Map;

public class PracticeB {
    Map<String,Integer> createUpdatedCollection(Map<String,Integer> collectionA, Map<String,List<String>> object) {
        //实现练习要求，并改写该行代码。
        Map<String, Integer> result=collectionA;
        Integer nvalue;
        int tempvalue=0;
        List<String> collectionvalue=object.get("value");
        for(String temp:collectionvalue){
            if(collectionA.containsKey(temp)){
                nvalue=collectionA.get(temp);
                tempvalue=nvalue.intValue();
                int sub=tempvalue/3;
                tempvalue=tempvalue-sub;
                nvalue=new Integer(tempvalue);
                result.put(temp,nvalue);
            }
        }
        return result;
    }
}
