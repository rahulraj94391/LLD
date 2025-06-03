package com.org.design.Behavioral.Template;

public abstract class PaymentFlowTemplate {
    public abstract void validateRequest();

    public abstract void calculateFee();

    public abstract void debitAmount();

    public abstract void creditAmount();

    public final void sendMoney() {
        // step 1
        validateRequest();

        // step 2
        debitAmount();

        // step 3
        calculateFee();

        // step 4
        creditAmount();
    }
}
