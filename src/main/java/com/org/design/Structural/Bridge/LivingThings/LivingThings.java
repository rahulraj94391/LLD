package com.org.design.Structural.Bridge.LivingThings;


import com.org.design.Structural.Bridge.breathing.BreatheImplementor;

public abstract class LivingThings {
    protected final BreatheImplementor breatheImplementor;

    public LivingThings(BreatheImplementor breatheImplementor) {
        this.breatheImplementor = breatheImplementor;
    }

    abstract public void breatheProcess();
}