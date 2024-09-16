package org.example;

import java.util.Objects;

public class Product {
    private double price;
    private int productID;
    private String name;

    public Product(int productID, double price, String name ) {
        this.productID = productID;
        this.price = price;
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(price, product.price) == 0 && productID == product.productID && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, productID, name);
    }
}
