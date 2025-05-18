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

    private final String level;// статус клиента 1 — обычный клиент,2 — серебряный,3 — золотой,4 — платиновый
//1 — regular customer,2 — silver,3 — gold,4 — platinum

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
