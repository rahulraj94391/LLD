package com.org.design.Structural.Adapter.Adapter;


import com.org.design.Structural.Adapter.Apaptee.WeightMachine;

public class WeightMachineAdapterImpl implements WeightMachineAdapter {
    final WeightMachine weightMachineImpl;

    public WeightMachineAdapterImpl(WeightMachine weightMachine) {
        this.weightMachineImpl = weightMachine;
    }

    @Override
    public double getWeightInKg() {
        double weightInPound = weightMachineImpl.getWeightInPound();
        double weightInKg = weightInPound * 0.45;
        return weightInKg;
    }
}
