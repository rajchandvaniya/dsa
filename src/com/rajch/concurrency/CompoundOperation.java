package com.rajch.concurrency;


import java.util.concurrent.atomic.AtomicInteger;

class Counter {
    int count;

    public void increment() {
        count++;
    }
}

class AtomicCounter {
    AtomicInteger count;

    public AtomicCounter() {
        this.count = new AtomicInteger();
    }

    public void increment() {
        count.incrementAndGet();
    }
}

public class CompoundOperation {
    public static void main(String[] args) throws InterruptedException {
        Counter c = new Counter();
        Thread t1 = new Thread(() -> {for(int i=0; i<100000; i++) c.increment();});
        Thread t2 = new Thread(() -> {for(int i=0; i<100000; i++) c.increment();});

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        // Expected is 200000, but since ++ is compound operation, it won't be 200000
        System.out.println("Count: " + c.count);

        AtomicCounter ac = new AtomicCounter();
        Thread t3 = new Thread(() -> {for(int i=0; i<100000; i++) ac.increment();});
        Thread t4 = new Thread(() -> {for(int i=0; i<100000; i++) ac.increment();});

        t3.start();
        t4.start();

        t3.join();
        t4.join();
        // Guaranteed to be 200000
        System.out.println("Atmoic Count: " + ac.count.get());
    }
}
