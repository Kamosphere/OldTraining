package cn.school.thoughtworks.section3;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class PracticeD {
    String[] shortlineDetect(String temp){
        String[] result=temp.split("-");
        return result;
    }
    Map<String,Integer> createUpdatedCollection(List<String> collectionA, Map<String,List<String>> object) {
        Map<String, Integer> map=new HashMap<String,Integer>();
        List<String> collectionvalue=object.get("value");
        Integer i,nvalue;
        int tempvalue=0;
        String key = null;
        for(String temp:collectionA) {
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
