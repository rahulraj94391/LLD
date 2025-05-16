package com.org.design.Behavioral.Strategy;

import java.awt.*;

public class StrategyRunner {
    public static void main(String[] args) {
        Point from = new Point(0, 0);
        Point to = new Point(12, 7);


        RouteStrategy publicTransportStrategy = new PublicTransportStrategy();
        RouteStrategy roadStrategy = new RoadStrategy();
        RouteStrategy walkingStrategy = new WalkingStrategy();

        Navigator navigator = new Navigator(publicTransportStrategy);
        navigator.buildRoute(from, to);
    }
}
