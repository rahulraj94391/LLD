package com.org.design.Creational.Prototype;

/**
 * When the object creation is an expensive task, then this
 * design pattern can be used to make the copy of existing
 * object. All the class properties, be it <b>public</b>
 * or <b>private</b>, will be same in the cloned object.
 * The client does not bother about knowing the properties. The
 * class is takes responsibility to create its own clone.
 */

public class PrototypeRunner {
    public static void main(String[] args) {
        client();
    }

    private static void client() {
        Student student = new Student(25, "Rahul", 43);
        Student cloneStudent = student.clone();
    }
}
