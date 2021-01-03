package com.crisper.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class test {
    public static void main(String... arg){
        List<String[]> list=new ArrayList<>();
        String[] slota={"room1","room2","room3"};
        String[] slotb={"on","off"};
        String[] slotc={"light","fan","lamp","tube"};
        int count=0;

        list.add(slota);
        list.add(slotb);
        list.add(slotc);

        String word="turn @slota @slotb @slotc";
        for(String a:slota){
            for (String c:slotc){
                for (String b:slotb){
                    String txt=word;
                    txt=txt.replace("@slotb",b);
                    txt=txt.replace("@slotc",c);
                    txt=txt.replace("@slota",a);
                    System.out.println(txt);
                    count++;
                }
            }
        }
        System.out.println(count);
    }
    public static void print( List<String[]> list){


    }
    void generatePermutations(List<List<String>> lists, List<String> result, int depth, String current) {
        if (depth == lists.size()) {
            result.add(current);
            return;
        }

        for (int i = 0; i < lists.get(depth).size(); i++) {
            generatePermutations(lists, result, depth + 1, current + lists.get(depth).get(i));
        }
    }
}
