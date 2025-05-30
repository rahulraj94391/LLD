package com.org.design.Structural.Adapter;

import com.org.design.Structural.Adapter.Adapter.WeightMachineAdapter;
import com.org.design.Structural.Adapter.Adapter.WeightMachineAdapterImpl;
import com.org.design.Structural.Adapter.Apaptee.WeightMachineImpl;

/**
 * <p> Take example of plug-socket example. Sockets differ from country to country, and we travel with international
 * power adapter to charge batteries of our devices. Here, our charger is 'Client', internation plug is 'Adapter'
 * and existing wall-socket of different countries is 'existing interface'. </p>
 * <br>
 * <p> <h3>Another example:</h3>
 * A company Zoho serves data, in form of XML,to external third party companies. Zoho decided to move from XML
 * to JSON. But they need to adapt the changes and keep on sending XML responses so that existing service does
 * not break for others. This can be done with an adapter, which transforms JSON to XML.
 * </p>
 */
public class AdapterRunner {
    public static void main(String[] args) {
        WeightMachineAdapter weightMachineAdapter = new WeightMachineAdapterImpl(new WeightMachineImpl());
        double weightInKg = weightMachineAdapter.getWeightInKg();
        System.out.println("weightInKg = " + weightInKg);
    }
}
