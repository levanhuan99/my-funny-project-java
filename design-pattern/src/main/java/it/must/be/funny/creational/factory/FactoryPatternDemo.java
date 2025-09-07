package it.must.be.funny.creational.factory;

class FactoryPattern {
    // Factory method
    public static Product createProduct(String type, String name, double price, String property) {
        switch (type) {
            case "A":
                return new ProductA(name, price, property);
            case "B":
                return new ProductB(name, price, property);
            default:
                throw new IllegalArgumentException("Unknown product type");
        }
    }
}

class ProductA extends Product {

    private String propertyA;
    public ProductA(String name, double price, String propertyA) {
        super(name, price);
        this.propertyA = propertyA;
    }

    @Override
    public String getInfo() {
        return this.getName() + " price is " + this.getPrice() + "$, Property A: " + this.propertyA;
    }

}

class ProductB extends Product {
    private String propertyB;
    public ProductB(String name, double price, String propertyB) {
        super(name, price);
        this.propertyB = propertyB;
    }

    @Override
    public String getInfo() {
        return this.getName() + " price is " + this.getPrice() + "$, Property B: " + this.propertyB;
    }

}

abstract class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public abstract String getInfo();
}

/**
 * - Định nghĩa: Factory Pattern là một mẫu thiết kế sáng tạo (creational design pattern) trong lập trình hướng đối tượng. 
 * - Mục đích chính của Factory Pattern là cung cấp một cách để tạo ra các đối tượng mà không cần phải chỉ định lớp cụ thể của đối tượng đó. 
 * - Thay vào đó, một phương thức (thường gọi là "factory method") sẽ chịu trách nhiệm tạo ra các đối tượng dựa trên các tham số đầu vào hoặc điều kiện nhất định.
 */
public class FactoryPatternDemo {
    public static void main(String[] args) {
        Product productA = FactoryPattern.createProduct("A", "Product A", 100, "Property A");
        System.out.println(productA.getInfo());

        Product productB = FactoryPattern.createProduct("B", "Product B", 200, "Property B");
        System.out.println(productB.getInfo());
    }
}