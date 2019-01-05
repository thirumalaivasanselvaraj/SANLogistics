package com.saneforce.logistics.Java_Class_Testing;

// abstract class
abstract class Shape {
    // declare fields
    String objectName = " ";

    Shape(String name) {
        this.objectName = name;
    }

    // declare non-abstract methods
    // it has default implementation
    @Deprecated
    public void moveTo(int x, int y) {
        System.out.println(this.objectName + " " + "has been moved to"
                + " x = " + x + " and y = " + y);
    }

    long k;

    // abstract methods which will be
    // implemented by its subclass(es)
    public abstract double area();

    public abstract void draw();
}

abstract class Animal {
    //abstract method

    Animal() {
        System.out.println("HAII");
    }

    public abstract void sound();

    //public abstract void bb();


}

//Dog class extends Animal class
class Dog {


    public static void main(String args[]) {
        Animal a = new Animal() {

            @Override
            public void sound() {

            }
        };
        a.sound();
        ;
    }


}

class SingleTonClass {
    //Static Class Reference
    private static SingleTonClass obj = null;

    private SingleTonClass() {

        System.out.println("THIS IS SINGLETON CLASS");
        /*Private Constructor will prevent
         * the instantiation of this class directly*/
    }

    public static void ggddddd() {
        System.out.println("vasan");
    }

    public static SingleTonClass objectCreationMethod() {
        /*This logic will ensure that no more than
         * one object can be created at a time */
        if (obj == null) {
            obj = new SingleTonClass();

        }
        return obj;
    }

    public static void display() {
        System.out.println("Singleton class Example");

    }

    public static void main(String args[]) {
        //Object cannot be created directly due to private constructor
        //This way it is forced to create object via our method where
        //we have logic for only one object creation
        SingleTonClass myobject = SingleTonClass.objectCreationMethod();
        bb n = new bb() {
            @Override
            public void thiru(String n, int b) {
                System.out.println(n);
            }

            @Override
            public void hai() {

            }
        };
        n.thiru("vxxxxxxxxxxasan", 0);
    }


    public static void kk() {
        SingleTonClass myobject = new SingleTonClass();


    }


}

interface bb {
    void thiru(String n, int b);

    void hai();

    class name {


    }
}

class hh implements bb {

    public void google() {
        System.out.println("");
    }

    @Override
    public void thiru(String n, int b) {
        System.out.println("VASAN" + n);
    }

    @Override
    public void hai() {
        System.out.println("mmmmm");
    }
}

class ccc {
    public static void main(String args[]) {
        bb nn = new bb() {

            @Override
            public void thiru(String n, int b) {
                System.out.println("sssssssssssss" + n);
            }

            @Override
            public void hai() {

            }
        };

        nn.thiru("google", 0);


    }
}// Java program to demonstrate objects

// passing to methods.
class ObjectPassDemo {
    int a, b;

    ObjectPassDemo(int i, int j) {
        a = i;
        b = j;
    }

    // return true if o is equal to the invoking
    // object notice an object is passed as an
    // argument to method
    boolean equalTo(ObjectPassDemo o) {
        System.out.println("GOGLE" + o.a);
        System.out.println("Thirumalai" + a);
        return (o.a == a && o.b == b);
    }
}

// Driver class
class Testt {
    public static void main(String args[]) {
        ObjectPassDemo ob1 = new ObjectPassDemo(100, 22);
        ObjectPassDemo ob2 = new ObjectPassDemo(100, 22);
        ObjectPassDemo ob3 = new ObjectPassDemo(-1, -1);

        Testt s = new Testt();
        System.out.println("OBJECT CREATION EXAMPLE" + s.vvv());
        System.out.println("ob1 == ob2: " + ob1.equalTo(ob2));
        System.out.println("ob1 == ob3: " + ob1.equalTo(ob3));
    }

    public boolean vvv() {
        ObjectPassDemo ob1 = new ObjectPassDemo(100, 22);
        return ob1.equalTo(ob1);
    }


}
