package cn.school.thoughtworks.section3;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class PracticeA {
    Map<String,Integer> createUpdatedCollection(Map<String,Integer> collectionA, Map<String,List<String>> object) {
        //实现练习要求，并改写该行代码。
        Map<String, Integer> result=collectionA;
        Integer nvalue;
        List<String> collectionvalue=object.get("value");
        for(String temp:collectionvalue){
            if(collectionA.containsKey(temp)){
                nvalue=collectionA.get(temp);
                nvalue=new Integer(nvalue.intValue()-1);
                result.put(temp,nvalue);
            }
        }
        return result;
    }
}
