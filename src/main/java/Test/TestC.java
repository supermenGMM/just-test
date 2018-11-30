package Test;

import javax.xml.bind.annotation.XmlAnyAttribute;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;



import java.util.*;
import java.util.logging.Logger;

public class TestC {
    public static void main(String[] args){
        List<Integer> list1
                = new ArrayList<Integer>();
        list1.add(1);
        List<Integer> list2
                = new ArrayList<Integer>();
        list2.add(2);
        list2.add(3);
        list1.addAll(list2);
        System.out.println(list1.size());

        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "3");
        map.put(2, "3");
        System.out.println(map.keySet().toString());
    }
}
