package com.org.design.Behavioral.Memento;

import java.util.ArrayList;
import java.util.List;

// Care Taker
public class ConfigurationCareTaker {
    final List<ConfigurationMemento> history = new ArrayList<>();

    public void addMemento(ConfigurationMemento memento) {
        history.add(memento);
    }

    public ConfigurationMemento undo() {
        if (history.isEmpty()) return null;

        //get the last Memento from the list
        ConfigurationMemento lastMemento = history.getLast();
        history.remove(lastMemento);
        return lastMemento;
    }
}
