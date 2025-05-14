package com.org.DesignPatterns.Behavioral.Observer;

public class ObserverRunner {
    public static void main(String[] args) {
        NewsLetterSubscriber person1 = new NewsLetterSubscriber("rahul raj", "rahul@gmail.com");
        NewsLetterSubscriber person2 = new NewsLetterSubscriber("akarshit kumar", "akarshit.kumar@gmail.com");
        NewsLetterSubscriber person3 = new NewsLetterSubscriber("Daya", "daya@gmail.com");


        Publisher publisher = new Publisher();
        publisher.addSubscriber(person1);
        publisher.addSubscriber(person2);
        publisher.addSubscriber(person3);

        System.out.println("product notification will be sent in 5 sec, please wait ...");

        Runnable runnable = () -> {
            try {
                Thread.sleep(5000);
                publisher.setDate("available");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Thread asyncNotificationThread = new Thread(runnable);
        asyncNotificationThread.start();

    }
}
