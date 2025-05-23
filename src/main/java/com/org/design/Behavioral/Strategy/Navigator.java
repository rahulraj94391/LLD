package com.org.design.Behavioral.Strategy;

import java.awt.*;

public class Navigator {
    private final RouteStrategy routeStrategy;

    public Navigator(RouteStrategy routeStrategy) {
        this.routeStrategy = routeStrategy;
    }

    public void buildRoute(Point A, Point B) {
        routeStrategy.buildRoute(A,B);
    }
}
