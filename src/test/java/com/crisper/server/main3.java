package com.crisper.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class main3 {

    public static void main(String[] args) {

        String text="turn @slota @slotb @slotc";
        List<List<String>> list=new ArrayList<>();
        int count=0;
        List<String> slota= Arrays.asList( "room","kitchen","my room");
        List<String> slotb=Arrays.asList("on","off");
        List<String> slotc=Arrays.asList("light","fan","lamp","alexa");
        list.add(slotb);

        list.add(slota);

        list.add(slotc);

        List<String> result=new ArrayList<>();
        generatePermutations(list,result,0,"");

        System.out.println("[");
        for (String slots:result){
            String[] slot=slots.split(",");
            String word=text;
            word=word.replace("@slota",slot[0]);
            word=word.replace("@slotb",slot[1]);
            word=word.replace("@slotc",slot[2]);
            System.out.println("\""+word+"\",");
        }
        System.out.println("]");
    }
    static void generatePermutations(List<List<String>> lists, List<String> result, int depth, String current) {
        if (depth == lists.size()) {
            result.add(current);
            return;
        }
        for (int i = 0; i < lists.get(depth).size(); i++) {
            generatePermutations(lists, result, depth + 1, current + lists.get(depth).get(i)+",");
        }
    }

}
