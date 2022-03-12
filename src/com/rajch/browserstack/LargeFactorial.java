package com.rajch.browserstack;

import java.math.BigInteger;
import java.util.ArrayList;

/*
* Problem Statement: https://practice.geeksforgeeks.org/problems/factorials-of-large-numbers2508/1
* */
public class LargeFactorial {
    public ArrayList<Integer> largeFactorial(int N) {
        BigInteger result = BigInteger.ONE;
        for(int i=2; i<=N; i++)
            result = result.multiply(BigInteger.valueOf(i));
        String resultStr = result.toString();
        ArrayList<Integer> resultList = new ArrayList<>();
        for(int i=0; i<resultStr.length(); i++) {
            resultList.add(Integer.parseInt(resultStr.substring(i, i+1)));
        }
        return resultList;
    }
}
