package org.example;

import java.util.Set;



public class Customer {
    private final Long id;

    public Long getId() {
        return id;
    }

    private final String name;

    public String getName() {
        return name;
    }

    private final String level;

    public String getLevel() {
        return level;
    }

    private final Set <Order> orders;

    public Set<Order> getOrders() {
        return orders;
    }
    public Customer (Long id, String name, String level, Set<Order> orders) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.orders = orders;
    }
}
