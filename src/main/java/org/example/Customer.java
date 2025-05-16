package org.example;

import java.util.Set;

//Покупатель

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
}
