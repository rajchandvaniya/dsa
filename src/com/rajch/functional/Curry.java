package com.rajch.functional;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Curry {
    public static void main(String[] args) {
        Function<Integer, Function<Integer, Integer>> curryAdd = n -> (i -> i + n);
        System.out.println(curryAdd.apply(2).apply(3));

        Zip<Integer> zip = (l1, l2) -> {
            List<List<Integer>> result = new ArrayList<>();
            if(l1.size() != l2.size()) throw new RuntimeException("Both lists should've equal length");
            for(int i=0; i<l1.size(); i++) {
                List<Integer> entry = new ArrayList<>();
                entry.add(l1.get(i));
                entry.add(l2.get(i));
                result.add(entry);
            }
            return result;
        };

        System.out.println(zip.apply(Arrays.asList(1,2,3,4,5), Arrays.asList(6,7,8,9,10)));
    }
}

@FunctionalInterface
interface Zip<T> {
    public List<List<T>> apply(List<T> list1, List<T> list2);
}