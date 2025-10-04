package com.org.design.Structural.Composite;

public class File implements Component {

    private final String fileName;

    public File(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void ls(int indentSpace) {
        StringBuilder sb = new StringBuilder();
        sb.repeat(" ", indentSpace);
        sb.append("File - ").append(fileName);
        System.out.println(sb);
    }
}
