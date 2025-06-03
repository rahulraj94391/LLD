package com.org.design.Behavioral.Template;

public class PaymentToFriend extends PaymentFlowTemplate {

    @Override
    public void validateRequest() {
        System.out.println("Validate logic of PayToFriend");
    }

    @Override
    public void calculateFee() {
        System.out.println("0% fee");
    }

    @Override
    public void debitAmount() {
        System.out.println("Debit logic to PayToFriend");
    }

    @Override
    public void creditAmount() {
        System.out.println("Credit logic to PayToFriend");
    }
}
