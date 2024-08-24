package it.must.be.funny.creational.factoryMethod;


public class TestFactoryMethod {
    public static void main(String[] args) {
        VehicleFactory carFactory = new CarFactory();
//        Vehicle car = carFactory.createVehicle();
        carFactory.driveVehicle();
        carFactory.getBrokenVehicle("cow");
        carFactory.fixVehicle();
//        car.getBrokenBySomething("cow");
//        car.fix();
        System.out.println("-------------");
        VehicleFactory bikeFactory = new BikeFactory();
        Vehicle bike = bikeFactory.createVehicle();
        bike.drive();
        bike.getBrokenBySomething("stole");
        bike.fix();
    }
}
