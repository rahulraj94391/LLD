package com.org.design.Behavioral.Memento;


// Originator (the real object)
public class ConfigurationOriginator {
    int height;
    int width;

    public ConfigurationOriginator(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public ConfigurationMemento createMemento() {
        return new ConfigurationMemento(this.height, this.width);
    }

    public void restoreMemento(ConfigurationMemento mementoToBeStored) {
        this.height = mementoToBeStored.height;
        this.width = mementoToBeStored.width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }


    @Override
    public String toString() {
        return "ConfigurationOriginator{" +
                "height=" + height +
                ", width=" + width +
                '}';
    }
}
