package com.saneforce.logistics.Java_Class_Testing;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GFGMain_Class {


    public static void main(String[] args) {

        // creating the Object of Rectangle class
        // and using shape class reference.
        Shape rect = new Rectangle(2, 3, "Rectangle");
        System.out.println("Area of rectangle: " + rect.area());
        rect.moveTo(1, 2);

        System.out.println(" ");

        // creating the Objects of circle class
        /*Shape circle = new Circle(2, "Cicle");
        System.out.println("Area of circle: " + circle.area());
        circle.moveTo(2,4);*/

    }
}


class A {

    String name = null;

    public void A(String n) {
        name = n;
        System.out.println("NAME" + name);

    }

    public static void name(String name) {
        System.out.println("thirumala" + name);
    }


}


class B extends A {

    public void sound() {

    }

    public void B(String nn) {
        super.name = nn;
    }

    @SuppressLint("NewApi")
    public static void main(String[] args) {


        A a = new B();

        A.name("podanaya");
    }
}


class Employee {
    private String name;
    private Integer age;
    private Double salary;

    public Employee(String name, Integer age, Double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public String toString() {
        return "Employee Name:" + this.name
                + "  Age:" + this.age
                + "  Salary:" + this.salary;
    }
//getters and setters for name, age and salary go here
//standard equals() and hashcode() code go here
}

//CollectionRemoveIfExample.java


class CollectionRemoveIfExample {
    static List<Employee> employeeList = new ArrayList<>();

    public static void main(String[] args) {
        employeeList.add(new Employee("Tom Jones", 45, 7000.00));
        employeeList.add(new Employee("Harry Major", 25, 10000.00));
        employeeList.add(new Employee("Ethan Hardy", 65, 8000.00));
        employeeList.add(new Employee("Nancy Smith", 22, 12000.00));
        employeeList.add(new Employee("Deborah Sprightly", 29, 9000.00));
        System.out.println("BEFORE ADD" + employeeList.iterator().toString());
        for (Iterator empIterator = employeeList.iterator();
             empIterator.hasNext(); ) {
            Employee emp = (Employee) empIterator.next();
            System.out.println("ERROR CORRECTion" + emp);
            if (emp.getAge() > 60) {
                empIterator.remove();
            }
        }

        System.out.println("Employees below the age of 30");

        for (Employee emp : employeeList) {

            System.out.println("OUTPUT" + emp);
        }
    }
}