package com.rajch.concurrency;

import java.util.concurrent.*;

class CallableWorker implements Callable<Integer> {
    @Override
    public Integer call() throws InterruptedException {
        Thread.sleep(2000);
        return 10;
    }
}

public class CallableExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        // Aproach 1
        ExecutorService executor = Executors.newFixedThreadPool(5);
        Future<Integer> result = executor.submit(new CallableWorker());
        System.out.println(result.get(3, TimeUnit.SECONDS));
        executor.shutdown();

        // Approach 2
        FutureTask<Integer> futureTask = new FutureTask<>(new CallableWorker());
        Thread task = new Thread(futureTask);
        task.start();
        System.out.println(futureTask.get());
    }
}
