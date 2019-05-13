package cn.school.thoughtworks.section1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PracticeC {
    List<String> collectSameElements(List<String> collection1, Map<String,List<String>> collection2) {
        List<String> result=new ArrayList<String>();
        List<String> collectionvalue=collection2.get("value");
        for(int i=0;i<collection1.size();i++){
            for(int j=0;j<collectionvalue.size();j++){
                if(collection1.get(i).equals(collectionvalue.get(j))){
                    result.add(collection1.get(i));
                    break;
                }
            }
        }
        return result;
    }
}
