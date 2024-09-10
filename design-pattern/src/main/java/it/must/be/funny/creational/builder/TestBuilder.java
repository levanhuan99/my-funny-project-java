package it.must.be.funny.creational.builder;

public class TestBuilder {
    public static void main(String[] args) {

        House.HouseBuilder houseBuilder = new House.HouseBuilder(2, "fancy").setGarage(true);
        House house = houseBuilder.build();
        System.out.println(house);

        houseBuilder.setMember(1);
        house = houseBuilder.getHouse();
        System.out.println(house);
    }
}
