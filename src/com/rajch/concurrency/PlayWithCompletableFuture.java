package com.rajch.concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class PlayWithCompletableFuture {
    static CompletableFuture<Integer> create() {
        return CompletableFuture.supplyAsync(PlayWithCompletableFuture::compute);
    }

    private static Integer compute() {
        System.out.println("compute -- "+Thread.currentThread());
//        sleep(100);
        return 2;
    }

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = create();
        System.out.println("Main -- "+Thread.currentThread());
        future.thenAccept(data -> printIt(data));
        System.out.println("here");
        sleep(100);
    }

    private static void printIt(Integer data) {
        sleep(1000);
        System.out.println(data + "-- "+Thread.currentThread());

    }
}
