package it.must.be.funny.creational.factoryMethod.manageVehicle;

public class Bike implements Vehicle{
    @Override
    public void drive() {
        System.out.println("do something with bike!!!");
    }

    @Override
    public void getBrokenBySomething(String stole) {
        System.out.printf("get broken by %s%n",stole);
    }

    @Override
    public void fix() {
        System.out.println("fixing bike!!!");
    }
}
