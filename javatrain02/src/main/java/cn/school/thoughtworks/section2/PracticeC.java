package cn.school.thoughtworks.section2;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class PracticeC {

    String[] shortlineDetect(String temp){
        String[] result=temp.split("-");
        return result;
    }
    String[] colonDetect(String temp){
        String[] result=temp.split(":");
        return result;
    }
    String[] bracketDetect(String temp){
        String[] result=new String[2];
        int start=temp.indexOf("[");
        int end=temp.indexOf("]");
        result[0]=temp.substring(0, start);
        result[1]=temp.substring(start+1, end);
        return result;
    }

    Map<String, Integer> countSameElements(List<String> collection1) {
        Map<String, Integer> map=new HashMap<String,Integer>();
        Integer i;
        String key = null;
        for(String temp:collection1) {
            if(temp.length()>1){
                String[] shortlineUnit=shortlineDetect(temp);
                if(shortlineUnit.length>1){
                    key=shortlineUnit[0];
                    i=Integer.valueOf(shortlineUnit[1]);
                }
                else {
                    String[] colonUnit=colonDetect(temp);
                    if(colonUnit.length>1){
                        key=colonUnit[0];
                        i=Integer.valueOf(colonUnit[1]);
                    }
                    else{
                        String[] bracketUnit=bracketDetect(temp);
                        key=bracketUnit[0];
                        i=Integer.valueOf(bracketUnit[1]);
                    }
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
