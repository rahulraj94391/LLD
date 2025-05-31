package com.org.design.Structural.Bridge.LivingThings;

import com.org.design.Structural.Bridge.breathing.BreatheImplementor;

public class Tree extends LivingThings {

    public Tree(BreatheImplementor breatheImplementor) {
        super(breatheImplementor);
    }

    @Override
    public void breatheProcess() {
        breatheImplementor.breathe();
    }
}
