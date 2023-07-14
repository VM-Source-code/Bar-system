package models;

import java.util.ArrayList;

public class Order {
    public String tableNumber;
    public ArrayList<Product> products;

    public Order(String tableNumber) {
        this.tableNumber = tableNumber;
        this.products = new ArrayList<>();
    }

    public double getTotalPrice(boolean haveDiscount) {
        double totalPrice = 0;
        for(Product product : products) {
            totalPrice += product.salePrice > 0 ? product.salePrice : product.price;
        }

        if(haveDiscount) {
            double discount = totalPrice * 0.2;
            return totalPrice - discount;
        } else {
            return totalPrice;
        }
    }
    public String getTotalPriceString(boolean haveDiscount) {
        double totalPrice = getTotalPrice(haveDiscount);
        String tpString = Double.toString(totalPrice);
        return "The bill for " + this.tableNumber + " table is " + tpString;
    }

    @Override
    public String toString() {
        return "Order{" +
                "tableNumber='" + tableNumber + '\'' +
                ", products=" + products +
                '}';
    }
}
