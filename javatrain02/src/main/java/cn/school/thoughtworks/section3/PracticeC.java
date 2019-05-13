package cn.school.thoughtworks.section3;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class PracticeC {
    Map<String,Integer> createUpdatedCollection(List<String> collectionA, Map<String,List<String>> object) {
        Map<String, Integer> map=new HashMap<String,Integer>();
        List<String> collectionvalue=object.get("value");
        Integer i,nvalue;
        int tempvalue=0;
        for(String temp:collectionA) {
            if (map.containsKey(temp)) {
                i = (Integer) map.get(temp);
                i = new Integer(i.intValue() + 1);
                map.put(temp, i);
            }
            else {
                map.put(temp, new Integer(1));
            }
        }
        for(String temp:collectionvalue){
            if(map.containsKey(temp)){
                nvalue=map.get(temp);
                tempvalue=nvalue.intValue();
                int sub=tempvalue/3;
                tempvalue=tempvalue-sub;
                nvalue=new Integer(tempvalue);
                map.put(temp,nvalue);
            }
        }
        return map;
    }
}

