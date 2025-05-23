package com.org.design.Creational.FactoryMethod;


/**
 * Factory method for creating instances of {@link Shape}.
 * <p>
 * This method encapsulates the instantiation logic for different implementations
 * of the {@link Shape}, and returns an appropriate instance based on input parameters.
 * </p>
 */


public class FactoryRunner {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape circle = shapeFactory.getShape("CIRCLE");
        circle.draw();
    }
}

class ShapeFactory {
    public Shape getShape(String shape) {
        switch (shape) {
            case "CIRCLE": {
                return new Circle();
            }
            case "RECTANGLE": {
                return new Rectangle();
            }
            default: {
                return null;
            }
        }
    }
}

class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Rectangle");
    }
}

class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Circle");
    }
}

interface Shape {
    void draw();
}