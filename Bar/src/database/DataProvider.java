package database;

import models.Order;
import models.Product;
import models.User;
import models.UserType;

import java.util.ArrayList;

public class DataProvider {

    public ArrayList<User> waitresses;
    public ArrayList<Product> products;
    public ArrayList<Order> orders;
    public User currentUser;

    public void loadData() {
        //Create and load waitresses
        waitresses = new ArrayList<>();
        User waitress1 = new User("Ivana", "0101", UserType.WAITRESS);
        User waitress2 = new User("Gergana", "1010", UserType.WAITRESS);
        User manager = new User("Georgi", "0000", UserType.MANAGER);
        waitresses.add(waitress1);
        waitresses.add(waitress2);
        waitresses.add(manager);

        //Create and load Products
        products = new ArrayList<>();

        Product whiskey = new Product("1", "Wiskey", "Jack Daniels", 50, 6.00, 0);
        Product whiskey2 = new Product("2", "Wiskey", "Johnny Walker Blue Label", 50, 45.00, 37.99);
        Product water = new Product("3", "Water", "Devin", 250, 2.00,0);
        Product nuts = new Product("4", "Nuts", "Almonds", 100, 8.00, 0);
        Product chocolate = new Product("5", "Sweet", "Chocolate", 1, 10.00, 0);
        Product whiskeyBottle = new Product("6", "Wiskey", "Jack Daniels - 0.7", 700, 84.00, 70.00);

        products.add(whiskey);
        products.add(whiskey2);
        products.add(water);
        products.add(nuts);
        products.add(chocolate);
        products.add(whiskeyBottle);

        //Create orders array to be ready for adding
        orders = new ArrayList<>();

    }

}
