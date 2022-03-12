package com.rajch.generics;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bag<E> {
    Map<E, Integer> items = new HashMap<>();

    public void add(E elem) {
        items.put(elem, items.getOrDefault(elem, 0) + 1);
    }

    public void remove(E elem) {
        if(items.get(elem) == 1) {
            items.remove(elem);
        } else if(items.get(elem) > 1) {
            items.put(elem, items.get(elem)-1);
        }
    }
}

class BagUtils {
    public static <T> void copyListToBag(Bag<T> bag, List<T> list) {
        for(T elem: list) bag.add(elem);
    }

    // PECS
    // Producer Extends, Consumer Super
    // Producer (Only Fetch), Consumer (Put values)
    public static <T> void copyListToBagV2(Bag<? super T> bag, List<? extends T> list) {
        for(T elem: list) bag.add(elem);
    }

    public static <T> Bag<T> toBag(T...arr) {
        Bag<T> bag = new Bag<>();
        for(T elem: arr) {
            bag.add(elem);
        }
        return bag;
    }
}


class Driver {
    public static void main(String[] args) {
        Bag<Integer> iBag = BagUtils.toBag(1,2,3,4);
        Bag<Object> oBag = BagUtils.toBag(1,2,3,"four");

        List<Integer> iList = Arrays.asList(5,6,7,8);
        List<Object> oList = Arrays.asList(5,6,7,"Eight");

        BagUtils.copyListToBag(iBag, iList); // works
//        BagUtils.copyListToBag(oBag, iList); // doesn't work, since both nag/list require same type
//                                              logically should be allowed, since Integer can be assigned to Object

        BagUtils.copyListToBagV2(iBag, iList);
        BagUtils.copyListToBagV2(oBag, iList); // works
    }
}