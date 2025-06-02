package com.org.design.Behavioral.Memento;

public class MementoRunner {
    public static void main(String[] args) {
        client();
    }

    private static void client() {
        ConfigurationCareTaker careTakerObject = new ConfigurationCareTaker();

        // Initialize State of the originator
        ConfigurationOriginator originatorObject = new ConfigurationOriginator(5, 10);

        //save it
        ConfigurationMemento snapshot1 = originatorObject.createMemento();

        //add to history
        careTakerObject.addMemento(snapshot1);

        // originator changing to new state
        originatorObject.setHeight(7);
        originatorObject.setWidth(12);

        //save it
        ConfigurationMemento snapshot2 = originatorObject.createMemento();

        //add to history
        careTakerObject.addMemento(snapshot2);

        // originator changing to new state
        originatorObject.setHeight(9);
        originatorObject.setWidth(14);


        //undo
        ConfigurationMemento restoredStateMemento = careTakerObject.undo();
        originatorObject.restoreMemento(restoredStateMemento);

        System.out.println(originatorObject);

    }
}
