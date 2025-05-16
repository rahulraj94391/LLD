package com.org.design.Behavioral.Observer;

public class NewsLetterSubscriber implements Subscriber {
    private String userName;
    private String emailId;

    public NewsLetterSubscriber(String userName, String emailId) {
        this.userName = userName;
        this.emailId = emailId;
    }

    @Override
    public void update(String message) {
        System.out.println("Update sent to - " + emailId + "\nHi, " + userName + " " + message);
    }
}
