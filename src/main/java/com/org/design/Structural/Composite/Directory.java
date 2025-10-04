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
    public void ls(int indentSpace) {
        StringBuilder sb = new StringBuilder();
        sb.repeat(" ", indentSpace);
        sb.append("Dir - ").append(directoryName);
        System.out.println(sb);
        for (Component object : objects) {
            object.ls(indentSpace + 4);
        }
    }

    public void addFiles(Component... components) {
        objects.addAll(Arrays.asList(components));
    }
}
