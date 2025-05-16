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
}
