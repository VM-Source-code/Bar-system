package models;
//Vodka Flirt 50ml 6.00
public class Product {
    public String id;
    public String type;
    public String brand;
    public int quantity;
    public double price;
    public double salePrice;

    public Product(String id, String type, String brand, int quantity, double price, double salePrice) {
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.quantity = quantity;
        this.price = price;
        this.salePrice = salePrice;
    }
    public String toString() {
        return this.id + "." + this.brand;
    }
}
