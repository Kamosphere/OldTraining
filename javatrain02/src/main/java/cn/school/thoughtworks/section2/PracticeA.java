package cn.school.thoughtworks.section2;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class PracticeA {
    Map<String, Integer> countSameElements(List<String> collection1) {
        Map<String, Integer> map=new HashMap<String,Integer>();
        Integer i;
        for(String temp:collection1) {
            if (map.containsKey(temp)) {
                i = (Integer) map.get(temp);
                i = new Integer(i.intValue() + 1);
                map.put(temp, i);
            }
            else {
                map.put(temp, new Integer(1));
            }
        }
        return map;
    }
}
