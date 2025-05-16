package com.org.design.Behavioral.Strategy;

import java.awt.*;

public class RoadStrategy implements RouteStrategy{
    @Override
    public void buildRoute(Point A, Point B) {
        System.out.println("Built Road Strategy from " + A + " to " + B);
    }
}
