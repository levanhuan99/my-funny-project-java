package it.must.be.funny.creational.factoryMethod.manageVehicle;

public class Car implements Vehicle{
    @Override
    public void drive() {
        System.out.println("do something with this car!!!");
    }

    @Override
    public void getBrokenBySomething(String stole) {
        System.out.printf("get broken by %s%n",stole);
    }

    @Override
    public void fix() {
        System.out.println("fixing car!!!");
    }
}
