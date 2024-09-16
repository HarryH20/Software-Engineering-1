package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;



public class ShoppingCartDemonstrationTest {
    enum ProductComparator {
        ASCENDING(Comparator.comparing(Product::getName)),
        DESCENDING(Comparator.comparing(Product::getName).reversed());

        private final Comparator<Product> comparator;

        ProductComparator(Comparator<Product> comparator) {
            this.comparator = comparator;
        }

        public Comparator<Product> getComparator() {
            return comparator;
        }
    }

    public static void main(String args[]) throws FileNotFoundException{

        File file = new File("shoppingCart.csv");
        Scanner scanner = new Scanner(file);
        List<Product> productsInCart = new ArrayList<>();

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] data = line.split(",");
            int productID = Integer.parseInt(data[0]);
            double price = Double.parseDouble(data[1]);
            String name = data[2];
            productsInCart.add(new Product(productID, price, name));

        }
        scanner.close();
        ShoppingCart cart = new ShoppingCart(productsInCart);

        File file2 = new File("reductions.csv");
        Scanner scanner2 = new Scanner(file2);
        Map<Product, Double> redMap = new HashMap<>();
        while (scanner2.hasNextLine()) {
            String line = scanner2.nextLine();
            String[] data = line.split(",");
            int productID = Integer.parseInt(data[0]);
            double price = Double.parseDouble(data[1]);
            String name = data[2];
            double reduction = Double.parseDouble(data[3]);
            if (reduction >= 0 && reduction <= 100) {
                redMap.put(new Product(productID, price, name), reduction);
            }
        }

        scanner2.close();


        double total = cart.calculateTotal(redMap,7.5 );
        System.out.println("Total: " + total);
        System.out.println();

        Set<Product> saleItems = new HashSet<>(redMap.keySet());
        Set<Product> itemsOnSale = cart.searchSaleItems(saleItems);
        System.out.println("Sale items: ");
        itemsOnSale.forEach(item -> System.out.println(item.getName()));
        System.out.println();

        System.out.println("Products ordered by name (Ascending):");
        productsInCart.sort(ProductComparator.ASCENDING.getComparator());
        for (Product product : productsInCart) {
            System.out.println(product.getName());
        }
        System.out.println();

        System.out.println("Products ordered by name (Descending):");
        productsInCart.sort(ProductComparator.DESCENDING.getComparator());
        for (Product product : productsInCart) {
            System.out.println(product.getName());
        }

    }
}
