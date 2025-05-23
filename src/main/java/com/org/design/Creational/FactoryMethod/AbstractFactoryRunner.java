package com.org.design.Creational.FactoryMethod;

public class AbstractFactoryRunner {
    public static void main(String[] args) {
        AbstractFactory abstractFactory = new AbstractFactory();

        VehicleFactory audiFactory = abstractFactory.getCarFactory("Audi");
        Vehicle q8 = audiFactory.getVehicle("Q8");
        q8.drive(); // O/P: Audi Q8 is driving.

        VehicleFactory volkswagenFactory = abstractFactory.getCarFactory("Volkswagen");
        Vehicle virtus = volkswagenFactory.getVehicle("Virtus");
        virtus.drive(); // O/P: Virtus GT is running.
    }
}


class AbstractFactory {
    public VehicleFactory getCarFactory(String brand) {
        return switch (brand) {
            case "Volkswagen" -> new VolkswagenFactory();
            case "Audi" -> new AudiFactory();
            default -> throw new IllegalStateException("No vehicle brand " + brand + " found.");
        };
    }
}

class VolkswagenFactory implements VehicleFactory {
    @Override
    public Vehicle getVehicle(String vehicleName) {
        return switch (vehicleName) {
            case "Polo" -> new Polo();
            case "Virtus" -> new Virtus();
            default -> throw new IllegalStateException("No vehicle of type " + vehicleName + " found.");
        };
    }
}


class AudiFactory implements VehicleFactory {
    @Override
    public Vehicle getVehicle(String vehicleName) {
        return switch (vehicleName) {
            case "A4" -> new AudiA4();
            case "Q8" -> new AudiQ8();
            default -> throw new IllegalStateException("No vehicle of type " + vehicleName + " found.");
        };
    }
}

interface VehicleFactory {
    Vehicle getVehicle(String vehicleName);
}

class Polo implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Polo is running.");
    }
}

class Virtus implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Virtus GT is running.");
    }
}


class AudiQ8 implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Audi Q8 is driving.");
    }
}

class AudiA4 implements Vehicle {

    @Override
    public void drive() {
        System.out.println("Audi A4 is driving.");
    }
}

interface Vehicle {
    void drive();
}


