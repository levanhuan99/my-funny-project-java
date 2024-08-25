package it.must.be.funny.creational.factoryMethod.manageVehicle;

public class BikeFactory extends VehicleFactory{
    @Override
    public Vehicle createVehicle() {
        return new Bike();
    }
}
