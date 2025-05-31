package com.org.design.Creational.Prototype;

public class Student implements Prototype {
    final int age;
    final String name;
    private final int rollNumber;


    public Student(int age, String name, int rollNumber) {
        this.age = age;
        this.name = name;
        this.rollNumber = rollNumber;
    }

    @Override
    public Student clone() {
        return new Student(age, name, rollNumber);
    }
}
