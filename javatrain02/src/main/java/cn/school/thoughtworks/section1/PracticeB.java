package cn.school.thoughtworks.section1;

import java.util.ArrayList;
import java.util.List;

public class PracticeB {
    List<String> collectSameElements(List<String> collection1, List<List<String>> collection2) {
        List<String> result=new ArrayList<String>();
        for(int k=0;k<collection2.size();k++) {
            List<String> collectiontemp = collection2.get(k);
            for (int i = 0; i < collection1.size(); i++) {
                for (int j = 0; j < collectiontemp.size(); j++) {
                    if (collection1.get(i).equals(collectiontemp.get(j))) {
                        result.add(collection1.get(i));
                        break;
                    }
                }
            }
        }
        return result;
    }
}
