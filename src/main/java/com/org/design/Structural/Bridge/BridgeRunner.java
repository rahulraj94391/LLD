package com.org.design.Structural.Bridge;

import com.org.design.Structural.Bridge.LivingThings.Fish;
import com.org.design.Structural.Bridge.LivingThings.LivingThings;
import com.org.design.Structural.Bridge.breathing.WaterBreatheImplementor;

/**
 * Bridge vs Strategy: The Bridge pattern does not change at run time, but different Strategy
 * patterns can be used at run time.
 * The UML diagram is very similar, here we should have a clear intent where to use which one.
 *
 * <p>Also see difference: "Bridge vs Strategy"</p>
 * @see <a href="https://youtu.be/SOw1_W0taBg?si=MPW7-75q3-DKbyAb&t=904">YT video</a>
 */
public class BridgeRunner {
    public static void main(String[] args) {
        client();
    }

    private static void client() {
        LivingThings fishObject = new Fish(new WaterBreatheImplementor());
        fishObject.breatheProcess();
    }
}
