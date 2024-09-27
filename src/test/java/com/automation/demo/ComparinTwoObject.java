package com.automation.demo;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
class Person {
    int age;
    String name;
}

public class ComparinTwoObject {
    public static void main(String[] args) {
        Person niyas = new Person();
        niyas.age = 21;
        niyas.name = "Niyas";

        Person manoj = new Person();
        manoj.name = "Niyas";
        manoj.age = 21;

        System.out.println(manoj.equals(niyas));
    }
}
