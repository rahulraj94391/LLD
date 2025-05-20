package com.org.concurrency;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorFrameworkRunner {
    public static void main(String[] args) {
        // countDownLatch();
        // invokeMultipleTasks();
        // executorServiceBasics();
    }

    private static void countDownLatch() {
        int numberOfDependentTasks = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CountDownLatch latch = new CountDownLatch(numberOfDependentTasks);

        executorService.submit(new DependentService(latch));
        executorService.submit(new DependentService(latch));
        executorService.submit(new DependentService(latch));
        try {
            // latch.await(5, TimeUnit.SECONDS);
            latch.await();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Main");
        executorService.shutdown();
    }

    private static void invokeMultipleTasks() {
        Callable<Integer> c1 = () -> {
            Thread.sleep(1000);
            System.out.println("Task 1");
            return 1;
        };

        Callable<Integer> c2 = () -> {
            Thread.sleep(1000);
            System.out.println("Task 2");
            return 2;
        };

        Callable<Integer> c3 = () -> {
            Thread.sleep(1000);
            System.out.println("Task 3");
            return 3;
        };

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Callable<Integer>> tasks = Arrays.asList(c1, c2, c3);
        List<Future<Integer>> futures = List.of();
        try {
            futures = executorService.invokeAll(tasks, 2900, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            executorService.shutdown();
        }
        try {
            for (var future : futures) {
                Integer res = future.get();
                System.out.println(res);
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }


        System.out.println("End of the program");
    }

    private static void executorServiceBasics() {
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 1; i < 10; i++) {
            int finalI = i;
            executorService.submit(() -> {
                long res = 1;
                for (int j = 1; j <= finalI; j++) res = res * j;
                System.out.println("fib of " + finalI + " is " + res);
            });
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {

        }
        System.out.println("Time taken = " + (System.currentTimeMillis() - start) + " ms");
    }
}

class DependentService implements Runnable {
    private final CountDownLatch countDownLatch;

    public DependentService(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " service started.");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            countDownLatch.countDown();
        }
    }
}






