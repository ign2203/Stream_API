package org.example;

import java.time.LocalDate;
import java.util.Set;

// Заказ
public class Order {
    private final Long id;

    public Long getId() {
        return id;
    }

    private final LocalDate orderDate; // в переводе Дата заказа

    public LocalDate getOrderDate() {
        return orderDate;
    }

    private final LocalDate deliveryDate; //deliveryDate - в переводе Дата доставки

    public LocalDate getDeliveryDate() {// для себя переменная LocalDate используется для работы с датами (год, месяц, день)
        return deliveryDate;
    }

    private final String status;
/*
CREATED     // только создан
SHIPPED     // передан в доставку
DELIVERED   // доставлен клиенту
CANCELLED   // отменён
 */
    public String getStatus() {
        return status;
    }

    private final Set<Product> products;

    public Set<Product> getProducts() {
        return products;
    }

    public Order(Long id, LocalDate orderDate, LocalDate deliveryDate, String status, Set<Product> products) {
        this.id = id; // номер заказа
        this.orderDate = orderDate; // дата заказа
        this.deliveryDate = deliveryDate; // дата поставки заказа
        this.status = status;
        this.products = products;
    }
}
