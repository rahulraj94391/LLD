package com.org.design.Behavioral.Template

class PayToMerchant : PaymentFlowTemplate() {
    override fun validateRequest() {
        println("Validate logic of PayToMerchant")
    }

    override fun calculateFee() {
        println("2% fee")
    }

    override fun debitAmount() {
        println("Debit logic of PayToMerchant")
    }

    override fun creditAmount() {
        println("Credit logic of PayToMerchant")
    }
}