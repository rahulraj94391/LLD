package com.org.design.Structural.Decorator;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCoupons {
    public static void shopping() {
        Product item1 = new Item1("FAN", 1000, ProductType.ELECTRONIC_GOODS);
        Product item2 = new Item2("SOFA", 2000, ProductType.FURNITURE_GOODS);

        ShoppingCart cart = new ShoppingCart();
        cart.addToCart(item1);
        cart.addToCart(item2);

        System.out.println(cart.getTotalPrice());
    }
}

class ShoppingCart {
    List<Product> productList;

    public ShoppingCart() {
        this.productList = new ArrayList<>();
    }

    public void addToCart(Product product) {
        Product productWithEligibleDiscount = new TypeCouponDecorator(new PercentageCouponDecorator(product, 10), 3, product.getType());
        productList.add(productWithEligibleDiscount);
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (Product product : productList) {
            totalPrice += (int) product.getPrice();
        }
        return totalPrice;
    }
}


class TypeCouponDecorator extends CouponDecorator {
    Product product;
    int discountPercentage;
    ProductType type;
    static List<ProductType> eligibleTypes = new ArrayList<>();

    static {
        eligibleTypes.add(ProductType.FURNITURE_GOODS);
        eligibleTypes.add(ProductType.DECORATIVE_GOODS);
    }

    public TypeCouponDecorator(Product product, int discountPercentage, ProductType type) {
        this.product = product;
        this.discountPercentage = discountPercentage;
        this.type = type;
    }

    @Override
    public double getPrice() {
        double price = product.getPrice();
        if (eligibleTypes.contains(type)) {
            return price - (price * discountPercentage) / 100;
        }
        return price;
    }
}

class PercentageCouponDecorator extends CouponDecorator {
    Product product;
    int discountedPercentage;

    public PercentageCouponDecorator(Product product, int discountedPercentage) {
        this.product = product;
        this.discountedPercentage = discountedPercentage;
    }

    @Override

    public double getPrice() {
        double price = product.getPrice();
        return price - (price * discountedPercentage) / 100;
    }
}


abstract class CouponDecorator extends Product {

}

abstract class Product {
    String name;
    double originalPrice;
    ProductType type;

    public Product() {
    }

    public Product(String name, double originalPrice, ProductType type) {
        this.name = name;
        this.originalPrice = originalPrice;
        this.type = type;
    }

    public abstract double getPrice();

    public ProductType getType() {
        return type;
    }
}

class Item1 extends Product {
    Item1(String name, double originalPrice, ProductType type) {
        super(name, originalPrice, type);
    }

    @Override
    public double getPrice() {
        return originalPrice;
    }
}

class Item2 extends Product {
    Item2(String name, double originalPrice, ProductType type) {
        super(name, originalPrice, type);
    }

    @Override
    public double getPrice() {
        return originalPrice;
    }
}


enum ProductType {
    ELECTRONIC_GOODS,
    FURNITURE_GOODS,
    DECORATIVE_GOODS
}

































































































