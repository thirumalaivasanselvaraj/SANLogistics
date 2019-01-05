package com.saneforce.logistics.Java_Class_Testing;

import java.util.ArrayList;
import java.util.Collections;

class Rectangle extends Shape {

    int length, width;

    // constructor
    Rectangle(int length, int width, String name) {

        super(name);
        this.length = length;
        this.width = width;
    }

    @Override
    public void draw() {
        System.out.println("Rectangle has been drawn ");
    }

    @Override
    public double area() {
        return (double) (length + width);
    }


}

class Student implements Comparable<Student> {
    int rollno;
    String name;
    int age;

    Student(int rollno, String name, int age) {
        this.rollno = rollno;
        this.name = name;
        this.age = age;
    }

    public int compareTo(Student st) {
        System.out.println("COMPARETO" + st.age);
        System.out.println("NORMAL AGE" + age);
        if (age == st.age)
            return 0;
        else if (age > st.age)
            return 1;
        else
            return -1;
    }

    public String toString() {
        return "thirumalai" + rollno + name + age;
    }


}

class TestSort1 {
    public static void main(String args[]) {
        ArrayList<Student> al = new ArrayList<Student>();
        al.add(new Student(101, "Vijay", 23));
        al.add(new Student(106, "Ajay", 27));
        al.add(new Student(109, "thiru", 93));
        al.add(new Student(105, "Jai", 21));
        System.out.println("BEFORE_SORTING" + al.toString());
        Collections.sort(al);
        for (Student st : al) {
            System.out.println("AFTER SORTING" + st.rollno + " " + st.name + " " + st.age);
        }
    }
}

class Parent1 {
    public static void Parent1() {
        System.out.println("Parent1");
    }
}

// Second Parent Class
class Parent2 extends Parent1 {
    public static void Parent1() {
        System.out.println("Parent2");
    }

    public static void main(String args[]) {

    }

    public void aVoid() {

    }
}

// Error : Test is inheriting from multiple
// classes
class Address {
    int streetNum;
    String city;
    String state;
    String country;

    Address(int street, String c, String st, String coun) {
        this.streetNum = street;
        this.city = c;
        this.state = st;
        this.country = coun;
        System.out.println("HI" + country);
    }
}

class StudentClass {
    int rollNum;
    String studentName;
    //Creating HAS-A relationship with Address class
    Address studentAddr;

    StudentClass(int roll, String name, Address addr) {
        this.rollNum = roll;
        this.studentName = name;
        this.studentAddr = addr;
    }

    public static void main(String args[]) {
        Address ad = new Address(55, "Agra", "UP", "India");

        StudentClass obj = new StudentClass(123, "Chaitanya", ad);
        System.out.println(obj.rollNum);
        System.out.println(obj.studentName);
        System.out.println(obj.studentAddr.streetNum);
        System.out.println(obj.studentAddr.city);
        System.out.println(obj.studentAddr.state);
        System.out.println(obj.studentAddr.country);
    }
}

class CarClass {
    String carName;
    int carId;

    CarClass(String name, int id) {
        this.carName = name;
        this.carId = id;
    }
}

class Driver extends CarClass {
    String driverName;

    Driver(String name, String cname, int cid) {
        super(cname, cid);
        this.driverName = name;
    }
}

class TransportCompany {
    public static void main(String args[]) {
        Driver obj = new Driver("Andy", "Ford", 9988);
        System.out.println(obj.driverName + " is a driver of car Id: " + obj.carId);
    }
}

class ABC {
    //Overridden method
    static public void disp() {
        System.out.println("disp() method of parent class");
    }
}

class Demo extends ABC {
    //Overriding method
    public void dispp() {
        System.out.println("disp() method of Child class");
    }

    @Deprecated
    public void newMethod() {
        System.out.println("new method of child class");
    }

    public static void main(String args[]) {
        /* When Parent class reference refers to the parent class object
         * then in this case overridden method (the method of parent class)
         *  is called.
         */
        ABC obj = new ABC();
        obj.disp();
        disp();
        /* When parent class reference refers to the child class object
         * then the overriding method (method of child class) is called.
         * This is called dynamic method dispatch and runtime polymorphism
         */
        new Demo().newMethod();

    }
}

class Base {

    // Static method in base class which will be hidden in subclass
    public void display() {
        System.out.println("Static or class method from Base");
    }

    // Non-static method which will be overridden in derived class
    public void print() {
        System.out.println("Non-static or Instance method from Base");
    }
}

// Subclass
class Derived extends Base {
    static int a = 1;

    // This method hides display() in Base
    public void display() {
        System.out.println("Static or class method from Derived" + a);
    }

    // This method overrides print() in Base
    public void print() {
        System.out.println("Non-static or Instance method from Derived");
    }
}

// Driver class
class Test {
    public static void main(String args[]) {
        Derived obj1 = new Derived();

        // As per overriding rules this should call to class Derive's static
        // overridden method. Since static method can not be overridden, it
        // calls Base's display()
        obj1.display();

        // Here overriding works and Derive's print() is called
        obj1.print();

    }
}

class VariableDemo {
    static int count = 0;

    public void increment() {
        count++;
    }

    public static void main(String args[]) {
        VariableDemo obj1 = new VariableDemo();
        VariableDemo obj2 = new VariableDemo();
        obj1.increment();
        System.out.println("Obj1: count is=" + obj1.count);
        obj2.increment();

        System.out.println("Obj2: count is=" + obj2.count);
    }
}

abstract class StaticDemo {
    public StaticDemo() {
        /*Constructor of this class*/
        System.out.println("StaticDemo");
    }
}

class StaticDemoChild extends StaticDemo {


    public StaticDemoChild() {
        super();
        /*By default super() is hidden here */
        System.out.println("StaticDemoChild");
    }

    private void display() {
        System.out.println("Just a method of child class");
    }

    public static void main(String args[]) {
        StaticDemoChild obj = new StaticDemoChild();
        obj.display();
    }
}

class Cov {

    public Cov fun() {
        int i = 0;
        System.out.println("BASE CLASS");
        return new Cov();
    }


}

class Cob extends Cov {
    @Override
    public Cob fun() {
        String i = "ggg";
        System.out.println("Chil CLass");
        return new Cob();
    }
}

class Main {
    public static void main(String args[]) {
        Cov obj = new Cob();
        obj.fun();

    }
}

class Operation {
    int data = 50;

    void change(Operation data) {
        System.out.println("DATA" + data.data);

        this.data -= data.data;//changes will be in the local variable only
    }





    public static void main(String args[]) {
        Operation op = new Operation();

        System.out.println("before change " + op.data);
        op.change(op);
        System.out.println("after change " + op.data);

    }
}