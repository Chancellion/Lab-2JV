abstract class Vehicle {
    protected String make;
    protected String model;
    protected int year;

    public Vehicle(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public abstract void start();
    public abstract void stop();
}

interface Refuelable {
    void refuel();
}

class Car extends Vehicle implements Refuelable {
    public Car(String make, String model, int year) {
        super(make, model, year);
    }

    @Override
    public void start() {
        System.out.println("Car is starting");
    }

    @Override
    public void stop() {
        System.out.println("Car is stopping");
    }

    @Override
    public void refuel() {
        System.out.println("Car is refueling");
    }
}

class Bike extends Vehicle {
    public Bike(String make, String model, int year) {
        super(make, model, year);
    }

    @Override
    public void start() {
        System.out.println("Bike is starting");
    }

    @Override
    public void stop() {
        System.out.println("Bike is stopping");
    }
}

class Truck extends Vehicle implements Refuelable {
    public Truck(String make, String model, int year) {
        super(make, model, year);
    }

    @Override
    public void start() {
        System.out.println("Truck is starting");
    }

    @Override
    public void stop() {
        System.out.println("Truck is stopping");
    }

    @Override
    public void refuel() {
        System.out.println("Truck is refueling");
    }
}

public class Task1 {
    public static void main(String[] args) {
                System.out.println("Данiїл Iванченко, КIб-1-23-4.0д:");
        Vehicle car = new Car("Toyota", "Corolla", 2020);
        Vehicle bike = new Bike("Yamaha", "MT-07", 2019);
        Vehicle truck = new Truck("Volvo", "FH16", 2021);

        car.start();
        car.stop();
        ((Refuelable) car).refuel();

        bike.start();
        bike.stop();

        truck.start();
        truck.stop();
        ((Refuelable) truck).refuel();
    }
}
