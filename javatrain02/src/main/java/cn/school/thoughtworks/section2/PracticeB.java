package cn.school.thoughtworks.section2;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class PracticeB {
    String[] shortlineDetect(String temp){
        String[] result=temp.split("-");
        return result;
    }

    Map<String, Integer> countSameElements(List<String> collection1) {
        Map<String, Integer> map=new HashMap<String,Integer>();
        Integer i;
        String key = null;
        for(String temp:collection1) {
            if(temp.length()>1){
                String[] pair=shortlineDetect(temp);
                if(pair.length>1){
                    key = pair[0];
                    i=Integer.valueOf(pair[1]);
                }
                else{
                    key=pair[0];
                    i=new Integer(1);
                }
            }
            else{
                key=temp;
                i=new Integer(1);
            }
            if (map.containsKey(key)) {
                i = new Integer(i.intValue() + map.get(key));
            }
                map.put(key, i);
        }
        return map;
    }
}
