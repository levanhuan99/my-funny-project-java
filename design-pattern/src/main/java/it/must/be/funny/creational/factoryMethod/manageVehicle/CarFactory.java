package it.must.be.funny.creational.factoryMethod.manageVehicle;

public class CarFactory extends VehicleFactory{

    @Override
    public Vehicle createVehicle() {
        return new Car();
    }
}
