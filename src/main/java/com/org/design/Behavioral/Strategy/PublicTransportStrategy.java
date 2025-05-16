package com.org.design.Behavioral.Strategy;

import java.awt.*;

public class PublicTransportStrategy implements RouteStrategy{
    @Override
    public void buildRoute(Point A, Point B) {
        System.out.println("Built Public Transport Strategy from " + A + " to " + B);
    }
}
