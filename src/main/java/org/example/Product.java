package org.example;

import java.math.BigDecimal;

public class Product {
    private final Long id;

    public Long getId() {
        return id;
    }

    private final String name;

    public String getName() {
        return name;
    }

    private final String category;

    public String getCategory() {
        return category;
    }

    private final BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public Product(Long id, String name, String category, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Товар |" +
                "id-" + id +
                ", Название-'" + name + '\'' +
                ", Категория-'" + category + '\'' +
                ", Цена- " + price + " руб.|";
    }
}
