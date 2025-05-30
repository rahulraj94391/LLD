package com.org.design.Structural.Facade;

/**
 * <p>Example: When a user is interacting with a car, it does not bother about
 * how the accelerating and braking works. The car provides simple pedals for
 * acceleration and breaking and hides the its internal complex working.</p>
 *
 * <br>
 * <p>When to use Facade Design Pattern: Whenever we want to hide the details
 * from the user, we may use this design pattern.</p>
 *
 * <p>Also see differences: "Facade vs Adapter", "Facade vs Proxy" </p>
 */

public class FacadeRunner {
    public static void main(String[] args) {
        clientRunner();
    }

    private static void clientRunner() {
        OrderFacade orderFacade = new OrderFacade();
        orderFacade.createOrder();
    }
}

class OrderFacade {
    private final ProductDAO productDAO;
    private final Invoice invoice;
    private final Payment payment;
    private final SendNotification notification;

    public OrderFacade() {
        this.productDAO = new ProductDAO();
        this.invoice = new Invoice();
        this.payment = new Payment();
        this.notification = new SendNotification();
    }


    /**
     * Let's say new steps will come in the future, still the client will call
     * this method and won't bother about the internal changes to make an order.
     */
    public void createOrder() {
        Product product = productDAO.getProduct(122);
        payment.makePayment();
        invoice.generateInvoice();
        notification.sendNotification();
        // future new steps
    }
}

class Invoice {
    public void generateInvoice() {
    }
}

class SendNotification {
    public void sendNotification() {
    }
}

class Payment {
    public boolean makePayment() {
        return true;
    }
}

class ProductDAO {
    public Product getProduct(int productId) {
        return new Product();
    }
}

class Product {

}