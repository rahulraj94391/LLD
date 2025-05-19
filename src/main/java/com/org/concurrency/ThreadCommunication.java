package com.org.concurrency;

/**
 * Without proper thread communication mechanism, threads might end up in an inefficient
 * busy-waiting state, leading to CPU resource wastage and potential deadlock.
 *
 * <p>For proper thread communication methods like notify(), notifyAll(), wait() can be used.</p>
 */
public class ThreadCommunication {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();
        Thread producerThread = new Thread(new Producer(resource), "Producer");
        Thread consumerOneThread = new Thread(new Consumer(resource), "Consumer");

        producerThread.start();
        consumerOneThread.start();
    }
}

/**
 * Shared resource used by both Producer and Consumer threads.
 * It holds an integer value and a flag to indicate whether the data is available or not.
 */
class SharedResource {
    private int data;
    private boolean hasData;

    /**
     * Produces a new value if the previous value has been consumed.
     *
     * <p>If the value has not yet been consumed ({@code hasData == true}), the producer waits.
     * When notified by the consumer, it sets the new value, marks {@code hasData = true},
     * and notifies all waiting threads (e.g., the consumer).</p>
     *
     * @param value the new data to produce
     */
    public synchronized void produce(int value) {
        while (hasData) {
            try {
                wait(); // Wait for the consumer to consume existing data
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        data = value;           // Set new data
        hasData = true;         // Mark data as available
        System.out.println(Thread.currentThread().getName() + " -> " + data);
        notifyAll();            // Notify waiting consumer(s)
    }

    /**
     * Consumes the current value if it is available.
     *
     * <p>If no value is available ({@code hasData == false}), the consumer waits.
     * When notified by the producer, it reads and prints the value,
     * marks {@code hasData = false}, and notifies all waiting threads (e.g., the producer).</p>
     */
    public synchronized void consume() {
        while (!hasData) {
            try {
                wait(); // Wait for the producer to produce new data
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        hasData = false;        // Mark data as consumed
        System.out.println(Thread.currentThread().getName() + " -> " + data);
        notifyAll();            // Notify waiting producer(s)
    }
}

/**
 * Producer thread implementation that generates integer values.
 * It produces 10 values in sequence and sends them to the shared resource.
 */
class Producer implements Runnable {
    private final SharedResource resource;

    /**
     * Constructor to inject the shared resource.
     *
     * @param resource the shared resource used for communication
     */
    public Producer(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        // Produce 10 values (0 through 9)
        for (int i = 0; i < 10; i++) {
            resource.produce(i);
        }
    }
}

/**
 * Consumer thread implementation that consumes values from the shared resource.
 * It consumes 10 values produced by the producer.
 */
class Consumer implements Runnable {
    private final SharedResource resource;

    /**
     * Constructor to inject the shared resource.
     *
     * @param resource the shared resource used for communication
     */
    public Consumer(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        // Consume 10 values
        for (int i = 0; i < 10; i++) {
            resource.consume();
        }
    }
}

