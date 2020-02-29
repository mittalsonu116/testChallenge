package api.tests;

import java.util.*;

public class Test2 {
        public static void main(String[] args) {
            String input = "This is my book. My book is amazing. I love my book.";
            String inputInLower=input.toLowerCase();
            Map<String,Integer> map=new TreeMap<>();
            String[] inputArr=inputInLower.replace(".","").split(" ");
            for(String str:inputArr){
                if(map.keySet().contains(str)){
                    map.put(str,map.get(str)+1);
                } else {
                    map.put(str,1);
                }
            }
            //map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEach(System.out::println);
            List<Map.Entry<String, Integer>> entrySet1 = new LinkedList<>(map.entrySet());
            System.out.println("printing map in sentence word's ascending order");
            entrySet1.stream().forEach(System.out::println);
            Collections.sort(entrySet1, (e1,e2)->e2.getValue().compareTo(e1.getValue()));
            System.out.println("printing map in sentence word's repetition reverse order ascending order");
            entrySet1.stream().forEach(System.out::println);
        }
}
