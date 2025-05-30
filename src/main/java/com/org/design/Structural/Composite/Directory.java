package com.org.design.Structural.Composite;

import java.util.ArrayList;
import java.util.Arrays;

public class Directory implements Component {
    private final String directoryName;
    private final ArrayList<Component> objects = new ArrayList<>();

    public Directory(String directoryName) {
        this.directoryName = directoryName;
    }

    @Override
    public void ls() {
        System.out.println("Dir - " + directoryName);
        for (Component object : objects) {
            object.ls();
        }
    }

    public void addFiles(Component... components) {
        objects.addAll(Arrays.asList(components));
    }
}
