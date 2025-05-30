package com.org.design.Structural.Composite;

public class File implements Component {

    private final String fileName;

    public File(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void ls() {
        System.out.println("File - " + fileName);
    }
}
