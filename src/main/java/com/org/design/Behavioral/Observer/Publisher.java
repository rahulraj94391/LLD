package com.org.design.Behavioral.Observer;

import java.util.HashSet;

public class Publisher {
    private final HashSet<Subscriber> subscriberList = new HashSet<>();

    private void notifySubscribers() {
        for (var subscribers : subscriberList) {
            subscribers.update("The xyz product is now available.");
        }
    }

    public void addSubscriber(Subscriber subscriber) {
        subscriberList.add(subscriber);
    }

    public boolean removeSubscriber(Subscriber subscriber) {
        return subscriberList.add(subscriber);
    }

    public void setDate(String data) {
        if (data.equals("available")) {
            notifySubscribers();
        }
    }
}
