package com.rajch.concurrency;


class Reader implements Runnable {

    private final BooleanWrapper flag;

    Reader(BooleanWrapper flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        while (!flag.flag) {
            Thread.yield();
        }
        System.out.println("Done");
    }
}

class BooleanWrapper {
    public boolean flag = false;
}

public class VolatileNeed {

    public static void main(String[] args) throws InterruptedException {
        BooleanWrapper flag = new BooleanWrapper();
        for(int i=0;i<10;i++)
            new Thread(new Reader(flag)).start();
        Thread.sleep(2000);
        flag.flag = true;

    }
}
