package org.example;

import java.sql.Date;

public class Product {
    String name, title, catalog;
    double price;
    Date created;

    public Product(String name, double price, String title, Date created, String catalog) {
        this.name = name;
        this.price = price;
        this.title = title;
        this.created = created;
        this.catalog = catalog;
    }
}
