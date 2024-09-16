package org.example;

import java.util.*;

public class ShoppingCart {
    private final List<Product> productList;

    ShoppingCart(List<Product> itemList){
        this.productList = itemList;
    }

    public double calculateTotal(Map<Product, Double> reduction, double salesTax){
        double total = 0.0;
        for(Product product: productList){
            Double discount = reduction.get(product);
            if (discount != null) {
                total += product.getPrice() * ((100 - discount) / 100);
            } else {
                total += product.getPrice();
            }
        }
        total += (1 + salesTax/100);
        return total;

    }

    public Set<Product> searchSaleItems(Set<Product> saleItems ){
        Set<Product> saleItemsInCart = new HashSet<>();
        for(Product item: productList){
            if(saleItems.contains(item)){
                saleItemsInCart.add(item);
            }
        }
        return saleItemsInCart;
    }

}
