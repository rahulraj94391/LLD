package com.org.design.Structural.Bridge.breathing;

public class LandBreatheImplementor implements BreatheImplementor{
    @Override
    public void breathe() {
        System.out.println("breath through the nose");
    }
}
