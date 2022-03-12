package com.rajch.generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GenericsBasics {
    public static void main(String[] args) {
        // 1.  container type is covariant, but payload is invariant
        ArrayList<Integer> ali = new ArrayList<>();
        List<Integer> li = ali;           // fine
        Collection<Integer> ci = ali;     // fine
        /*
        List<java.lang.Number> ln = ali;  // compile error
        Collection<Object> co = ali;      // compile error
        List<Object> lo = ali;            // compile error
        */

        List l = ali; // allowed but at the cost of type safety, can lead to ClassCastException
        l.add("String");
        Integer i = ali.get(0); // ClassCastException
        System.out.println(i);

        // 2. array is covariant in java
        Object[] arr = new Integer[10];
        arr[0] = 1;
        System.out.println(arr);

        // 3. But parameterized types are not, hence array of parameterized type not allowed
        /*
        List<Integer>[] liatArr = new ArrayList<Integer>[10]; // not allowed, compile error
        */
        List<String>[] wordlist = new ArrayList[10]; // allowed, but at the cost of type safety

        List<Integer> iList = new ArrayList<>();
        iList.add(10);

        Object[] oArr = wordlist; // allowed since array is covariant
        oArr[0] = iList;          // storing list of integer in array of list of String

        String str = wordlist[0].get(0); // ClassCastException
        System.out.println(str);
    }
}
