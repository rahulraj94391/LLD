package com.org.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Demonstrates the use and internal behavior of {@link java.util.concurrent.locks.ReentrantLock}.
 *
 * <p><b>Why use ReentrantLock?</b></p>
 * <ul>
 *   <li><b>Explicit Locking:</b> Provides explicit {@code lock()} and {@code unlock()} methods,
 *       offering more flexibility than {@code synchronized} blocks.</li>
 *   <li><b>Try-Locking with Timeout:</b> Allows timed attempts to acquire a lock using
 *       {@code tryLock(long timeout, TimeUnit unit)}.</li>
 *   <li><b>Interruptible Lock Acquisition:</b> Supports lock acquisition that can respond
 *       to thread interruptions via {@code lockInterruptibly()}.</li>
 *   <li><b>Fairness:</b> Can enforce first-come-first-served order using {@code new ReentrantLock(true)}.</li>
 *   <li><b>Condition Support:</b> Allows the creation of multiple {@code Condition} objects
 *       for advanced thread coordination (similar to {@code wait()} / {@code notify()}).</li>
 * </ul>
 *
 * <p><b>Reentrant Behavior and Hold Count:</b></p>
 * <ul>
 *   <li>The lock is <i>reentrant</i>, meaning the same thread can acquire it multiple times
 *       without blocking itself.</li>
 *   <li>Each successful lock acquisition by the same thread increases an internal <b>hold count</b>.</li>
 *   <li>Each call to {@code unlock()} reduces the count. The lock is fully released when the count reaches zero.</li>
 * </ul>
 *
 * <p><b>Internal Mechanism:</b></p>
 * <ul>
 *   <li>Internally, {@code ReentrantLock} uses an {@code AbstractQueuedSynchronizer} (AQS)
 *       to manage state and thread ownership.</li>
 *   <li>The {@code state} variable holds the count of reentrant acquisitions.</li>
 *   <li>Ownership is tracked to ensure only the owning thread can release the lock.</li>
 * </ul>
 *
 * <p><b>Example:</b></p>
 * <pre>{@code
 * ReentrantLock lock = new ReentrantLock();
 * lock.lock();   // state = 1
 * lock.lock();   // state = 2
 * lock.unlock(); // state = 1
 * lock.unlock(); // state = 0 (lock is released)
 * }</pre>
 *
 * @see java.util.concurrent.locks.ReentrantLock
 * @see java.util.concurrent.locks.Condition
 * @see java.util.concurrent.locks.AbstractQueuedSynchronizer
 */


class ReentrantExample {
    private final Lock lock = new ReentrantLock();

    public void outerMethod() {
        lock.lock();
        try {
            System.out.println("Outer method");
            innerMethod();
        } finally {
            lock.unlock();
        }
    }

    private void innerMethod() {
        lock.lock();
        try {
            System.out.println("Inner Method");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantExample example = new ReentrantExample();
        example.outerMethod();
    }
}