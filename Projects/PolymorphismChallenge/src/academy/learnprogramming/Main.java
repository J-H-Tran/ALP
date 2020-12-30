package academy.learnprogramming;

class Car {
    private String name;
    private int numCylinders;
    private int numWheels;
    private boolean engine;
    private int speed;

    public Car(int numCylinders, String name) {
        this.name = name;
        this.numCylinders = numCylinders;
        this.numWheels = 4;
        this.engine = true;
    }

    public String startEngine() {
        return "Car -> startEngine()";

    }

    public String accelerate() {
        return "Car -> accelerate()";
    }

    public String brake() {
        return "Car -> brake()";
    }

    public String getName() {
        return name;
    }

    public int getNumCylinders() {
        return numCylinders;
    }

    public int getNumWheels() {
        return numWheels;
    }

    public boolean isEngine() {
        return engine;
    }
}

class Corvette extends Car {
    public Corvette(int cylinders, String name) {
        super(cylinders, name);
    }

    @Override
    public String startEngine() {
        return "Corvette -> startEngine()";
    }

    @Override
    public String accelerate() {
        return "Corvette -> accelerate()";
    }

    @Override
    public String brake() {
        return "Corvette -> brake()";
    }
}

class Chevy extends Car {
    public Chevy(int cylinders, String name) {
        super(cylinders, name);
    }

    @Override
    public String startEngine() {
        return "Chevy -> startEngine()";
    }

    @Override
    public String accelerate() {
        return "Chevy -> accelerate()";
    }

    @Override
    public String brake() {
        return "Chevy -> brake()";
    }
}

class Mustang extends Car {
    public Mustang(int cylinders, String name) {
        super(cylinders, name);
    }

    @Override
    public String startEngine() {
        return getClass().getSimpleName() + " -> startEngine()";
    }

    @Override
    public String accelerate() {
        return getClass().getName() + " -> accelerate()";
    }

    @Override
    public String brake() {
        return getName() + " -> brake()";
    }
}

class Tesla extends Car {
    public Tesla(int cylinders, String name) {
        super(cylinders, name);
    }

}

class Camry extends Car {
    public Camry(int cylinders, String name) {
        super(cylinders, name);
    }

}

public class Main {

    public static void main(String[] args) {
        /* Polymorphism Challenge
         * Create a base class called Car
         * It should contain a few fields that would be appropriate for a generic car class:
         * Engine, Cylinders, Wheels, etc
         * Constructor should initialize cylinders (number of) and name, and set wheels to 4
         * and engine to true. Cylinders and names would be passed parameters
         *
         * Create appropriate getters
         *
         * Create some methods like startEngine, accelerate, and brake
         *
         * show a message for each in the base class
         * Create 3 sub classes for your favorite vehicles
         * Override the appropriate methods to demonstrate polymorphism in use
         * put all classes in the one java file*/

        Car car = new Car(4, "BaseCar");
        System.out.println(car.startEngine());
        System.out.println(car.accelerate());
        System.out.println(car.brake());

        Corvette corvette = new Corvette(8, "RedRider");
        System.out.println(corvette.startEngine());
        System.out.println(corvette.accelerate());
        System.out.println(corvette.brake());

        Mustang mustang = new Mustang(8, "BlueRider");
        System.out.println(mustang.startEngine());
        System.out.println(mustang.accelerate());
        System.out.println(mustang.brake());

        Chevy chevy = new Chevy(8, "BlueRider");
        System.out.println(chevy.startEngine());
        System.out.println(chevy.accelerate());
        System.out.println(chevy.brake());

    }

}
