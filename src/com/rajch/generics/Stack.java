package com.rajch.generics;

import java.util.LinkedList;
import java.util.List;

public class Stack<T> {
    LinkedList<T> stack;

    public Stack() {
        stack = new LinkedList<T>();
    }

    void push(T elem) {
        stack.addFirst(elem);
    }

    T top() {
        return stack.getFirst();
    }
    void pop() {
        stack.removeFirst();
    }
    int size() {
        return stack.size();
    }

}

class DriverStack {
    public static void main(String[] args) {
//        Stack<String> sStack = new Stack<>();
//        sStack.push("Raj");
//        System.out.println("Top: " + sStack.top());
//        sStack.push("Chandvaniya");
//        System.out.println("Top: " + sStack.top());
//        sStack.pop();
//        sStack.pop();
//        sStack.pop(); // RuntimeException

        Base base = new Base();
        System.out.println(base.sampleWithReturnAndArg("hi"));
        base = new Derived();
        System.out.println(base.sampleWithReturnAndArg("hello"));
    }
}

class Base {
    public void sample() {
        System.out.println("Base: Sample");
    }

    public void justAnotherSample() {
        System.out.println("Base: JustAnotherSample");
    }

    public Number sampleWithReturnAndArg(Object sample) {
        System.out.println("Base: sampleWithReturnAndArg "+sample);
        return 10.56f;
    }
}

class Derived extends Base {
    @Override
    public Integer sampleWithReturnAndArg(Object sample) {
        System.out.println("Derived: sampleWithReturnAndArg "+sample);
        return 10;
    }
/*
    public int justAnotherSample() { // compile error
        System.out.println("Derived: JustAnotherSample");
        return 10;
    }
*/
}