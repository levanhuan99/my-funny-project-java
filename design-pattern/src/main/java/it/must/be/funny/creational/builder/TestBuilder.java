package it.must.be.funny.creational.builder;

public class TestBuilder {
    public static void main(String[] args) {

        House myHouse = new House.HouseBuilder(3,"normal").build();
        System.out.println(myHouse);
        myHouse.setHasGarage(true);
        System.out.println(myHouse);
    }
}
