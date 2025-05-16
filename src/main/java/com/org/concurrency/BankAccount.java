package com.org.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private final Lock lock = new ReentrantLock();
    private int balance = 101;

    public void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " attempting to withdraw " + amount);
        /*if (balance >= amount) {
            System.out.println(Thread.currentThread().getName() + " proceeding with withdrawal ");
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {

            }
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " completed withdrawal. Remaining balance = " + amount);
        }*/
        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                if (balance >= amount) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " proceeding with withdrawal");
                        Thread.sleep(3000);
                        balance -= amount;
                        System.out.println(Thread.currentThread().getName() + " completed withdrawal. Remaining balance = " + amount);
                    } catch (Exception e) {

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

        }
    }
}
