package com.rajch.concurrency;

import com.sun.corba.se.spi.orbutil.threadpool.Work;
import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

class Worker extends Thread {
    @Override
    public void run() {
        System.out.println(super.getName() + " starting to sleep");
        try {
            super.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(super.getName() + " sleep finished");
    }
}

class WorkerRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "starting to sleep");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "sleep finished");
    }
}

public class ThreadCreationBasic {
    public static void main(String[] args) throws InterruptedException {
        // extending thread class
        for(int i=0; i<5; i++) new Worker().start();

        // implementing runnable
        new Thread(new WorkerRunnable()).start();

        // using lambda for runnable
        new Thread(() -> {
            System.out.println("Hi");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Bye");
        }).start();

        // using thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i=0; i<20; i++) {
            executorService.submit(() -> {
                System.out.println("Pool: " + Thread.currentThread().getName());
            });
        }
        Thread.sleep(2000);
        executorService.shutdownNow();
    }
}
