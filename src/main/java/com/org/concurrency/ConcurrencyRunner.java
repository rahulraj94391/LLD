package com.org.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrencyRunner {
    public static void main(String[] args) {
        BankAccount sbi = new BankAccount();
        Runnable task = () -> sbi.withdraw(50);

        Thread t1 = new Thread(task, "Thread 1");
        Thread t2 = new Thread(task, "Thread 2");

        t1.start();
        t2.start();
    }
}


class BankAccount {
    private final Lock lock = new ReentrantLock();
    private int balance = 101;

    public void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " attempting to withdraw " + amount);
        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                if (balance >= amount) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " proceeding with withdrawal");
                        Thread.sleep(3000);
                        balance -= amount;
                        System.out.println(Thread.currentThread().getName() + " completed withdrawal. Remaining balance = " + amount);
                    } catch (Exception e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        lock.unlock();
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + " insufficient balance" + amount);
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " could not acquire lock, try again later.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}