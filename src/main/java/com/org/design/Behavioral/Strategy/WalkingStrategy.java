package com.org.design.Behavioral.Strategy;

import java.awt.*;

public class WalkingStrategy implements RouteStrategy{
    @Override
    public void buildRoute(Point A, Point B) {
        System.out.println("Built Walking Strategy from " + A + " to " + B);
    }
}
