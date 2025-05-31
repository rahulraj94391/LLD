package com.org.design.Structural.Bridge.LivingThings;

import com.org.design.Structural.Bridge.breathing.BreatheImplementor;

public class Dog extends LivingThings {

    public Dog(BreatheImplementor breatheImplementor) {
        super(breatheImplementor);
    }

    @Override
    public void breatheProcess() {
        breatheImplementor.breathe();
    }
}
