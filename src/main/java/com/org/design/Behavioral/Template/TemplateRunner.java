package com.org.design.Behavioral.Template;

public class TemplateRunner {
    public static void main(String[] args) {
        client();
    }

    private static void client() {
        PaymentToFriend payToFriend = new PaymentToFriend();
        payToFriend.sendMoney();

        PayToMerchant payToMerchant = new PayToMerchant();
        payToMerchant.sendMoney();
    }
}
