package com.org.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * A ReentrantLock with an optional fairness policy.
 *
 * <p>When fairness is set to {@code true}, the lock favors granting access
 * to the longest-waiting thread. This ensures a first-come-first-served (FIFO)
 * access order, which helps prevent thread starvation.
 *
 * <p>When fairness is {@code false} (the default), the lock does not guarantee
 * any particular access order. Threads may acquire the lock out of order, which
 * can lead to higher throughput but may cause starvation for some threads.
 *
 * <p>Example usage:
 * <pre>{@code
 * ReentrantLock fairLock = new ReentrantLock(true);   // Fair lock (FIFO)
 * ReentrantLock unfairLock = new ReentrantLock(false); // Unfair lock (default)
 * }</pre>
 *
 * @see java.util.concurrent.locks.ReentrantLock
 */

public class FairnessExample {
    private final Lock lock = new ReentrantLock(true);

    private void accessResource() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " acquired the lock.");
            Thread.sleep(500);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.println(Thread.currentThread().getName() + " released the lock.");
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        FairnessExample example = new FairnessExample();
        Runnable task = example::accessResource;

        Thread t1 = new Thread(task, "Thread - 1");
        Thread t2 = new Thread(task, "Thread - 2");
        Thread t3 = new Thread(task, "Thread - 3");

        try {
            t1.start();
            Thread.sleep(50);
            t2.start();
            Thread.sleep(50);
            t3.start();
        } catch (Exception e) {

        }
    }
}
