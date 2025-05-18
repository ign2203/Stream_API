package org.example;

import java.time.LocalDate;
import java.util.Set;

public class Order {
    private final Long id;
    private final LocalDate orderDate;
    private final LocalDate deliveryDate;
    private final String status;
    private final Set<Product> products;

    public Long getId() {
        return this.id;
    }

    public LocalDate getOrderDate() {
        return this.orderDate;
    }

    public LocalDate getDeliveryDate() {
        return this.deliveryDate;
    }

    public String getStatus() {
        return this.status;
    }

    public Set<Product> getProducts() {
        return this.products;
    }

    public Order(Long id, LocalDate orderDate, LocalDate deliveryDate, String status, Set<Product> products) {
        this.id = id;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.products = products;
    }

    public String toString() {
        Long var10000 = this.id;
        return "Заказ |id-" + var10000 + ", Дата заказа -'" + String.valueOf(this.orderDate) + "', Дата поставки-'" + String.valueOf(this.deliveryDate) + "', Статус-'" + this.status + "|";