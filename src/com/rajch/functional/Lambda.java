package com.rajch.functional;

import java.util.function.Consumer;
import java.util.function.Function;

public class Lambda {
    Function<Integer, Integer> consumer = i -> {
        System.out.println(this.toString()); // Prints Lambda as the class, lambda expression doesnt create a new object
        return i*i;
    };

    public static void main(String[] args) {
        System.out.println(apply(10, new SquareIt()));
        Lambda l = new Lambda();
        l.consumer.apply(10);
    }

    static int apply(int n, Function<Integer,Integer> f) {
        return f.apply(n);
    }
}

class SquareIt implements Function<Integer, Integer> {
    @Override
    public Integer apply(Integer n) {
        System.out.println(this.toString());
        return n*n;
    }
}
