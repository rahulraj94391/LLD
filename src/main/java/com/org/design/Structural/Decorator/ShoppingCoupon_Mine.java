package com.org.design.Structural.Decorator;

import java.util.ArrayList;
import java.util.Arrays;

public class ShoppingCoupon_Mine {
    public static void shoppingCoupon() {
        ShoppingCart_Mine cart = new ShoppingCart_Mine();
        BaseProduct_Mine toothpaste = new ProductMine("Colgate", 120);
        BaseProduct_Mine soap = new ProductMine("Vim", 100);
        BaseProduct_Mine iphone = new ProductMine("iPhone", 120000);
        cart.addItem(toothpaste, soap, iphone);
        cart.applyCoupons();
        cart.printCartItems();
    }
}

class ShoppingCart_Mine {
    private final ArrayList<BaseProduct_Mine> cartItem = new ArrayList<>();
    private final ArrayList<BaseProduct_Mine> checkOutItem = new ArrayList<>();

    public void addItem(BaseProduct_Mine... products) {
        cartItem.addAll(Arrays.asList(products));
    }

    public void applyCoupons() {
        for (BaseProduct_Mine baseProductMine : cartItem) {
            baseProductMine = new FivePercentGeneralOff(baseProductMine);
            baseProductMine = new PhoneCouponMine(baseProductMine);
            checkOutItem.add(new ProductMine(baseProductMine.getName(), baseProductMine.getPrice()));
        }
    }

    public void printCartItems() {
        for (var item : checkOutItem) {
            System.out.printf("%-" + 40 + "s %6.2f\n", item.getName(), item.getPrice());
        }
    }
}

class PhoneCouponMine extends Coupon_Mine {
    public PhoneCouponMine(BaseProduct_Mine baseProductMine) {
        super(baseProductMine);
    }

    @Override
    public double getPrice() {
        if (baseProductMine.getName().contains("iPhone")) {
            return baseProductMine.getPrice() * 0.95;
        }
        return baseProductMine.getPrice();
    }

    @Override
    public String getName() {
        if (baseProductMine.getName().contains("iPhone")) {
            return baseProductMine.getName() + ", -5% off on iPhones";
        }
        return baseProductMine.getName();
    }
}

class FivePercentGeneralOff extends Coupon_Mine {
    public FivePercentGeneralOff(BaseProduct_Mine baseProductMine) {
        super(baseProductMine);
    }

    @Override
    public double getPrice() {
        return baseProductMine.getPrice() * 0.95;
    }

    @Override
    public String getName() {
        return baseProductMine.getName() + ", -5%";
    }
}


abstract class Coupon_Mine implements BaseProduct_Mine {
    BaseProduct_Mine baseProductMine;

    public Coupon_Mine(BaseProduct_Mine baseProductMine) {
        this.baseProductMine = baseProductMine;
    }
}

class ProductMine implements BaseProduct_Mine {
    private final String productName;
    private final double productPrice;

    public ProductMine(String productName, double productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }

    @Override
    public double getPrice() {
        return productPrice;
    }

    @Override
    public String getName() {
        return productName;
    }
}

interface BaseProduct_Mine {
    double getPrice();

    String getName();
}

