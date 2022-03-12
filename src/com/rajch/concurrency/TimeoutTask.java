package com.rajch.concurrency;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeoutTask {
    public static void main(String[] args) throws InterruptedException {
        Thread task = new Thread(() -> {
            while(!Thread.currentThread().isInterrupted()) {
                System.out.println("working...");
            }
            System.out.println("Don't like someone interrupting me, stopping the work");
        });
        task.start();

        // Approach 1
//        Thread.sleep(30000);
//        task.interrupt();

        // Approach 2
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.schedule(() -> task.interrupt(), 30, TimeUnit.SECONDS);
    }
}
