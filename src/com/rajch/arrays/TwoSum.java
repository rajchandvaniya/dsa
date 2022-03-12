package com.rajch.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TwoSum {
    public static void main(String[] args) {
//        stockPairs(Arrays.asList(5,7,9,13,11,6,6,3,3,5,7), 12);
        Integer i = 10;
        i++;
        assert i==null && i>=0;
        System.out.println(i);
    }

    public static int stockPairs(List<Integer> stockProfit, long target) {
        // To store unique pairs
        Set<Set<Long>> pairs = new HashSet<>();

        // Two Sum problem
        Set<Long> lookup = new HashSet<>();
        for(int i=0; i<stockProfit.size(); i++) {
            long key = target - stockProfit.get(i);
            if(lookup.contains(key)) {
                pairs.add(new HashSet<Long>(Arrays.asList((long)stockProfit.get(i), key)));
                lookup.remove(stockProfit.get(i));
            } else {
                lookup.add((long)stockProfit.get(i));
            }
        }
        return pairs.size();
    }
}
