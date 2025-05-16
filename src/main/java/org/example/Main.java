package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Product apple = new Product(147L, "Яблоко", "Фрукты", new BigDecimal("204.56"));
        Product banana = new Product(258L, "Бананы", "Фрукты", new BigDecimal("170.56"));// почему в id если поставить L - то все работает, если поставить A или другую любую букву, то
        // будет ошибка, это связано с тем что id типа LONG, и зачем мы вообще принимаем данный тип, к этой переменной?)
        Product cleaner = new Product(369258L, "Пылесос SAMSUNG", "Бытовая техника", new BigDecimal(9999.00);
        Product iphone = new Product(10L, "IPHONE APPLE 14", "Телефоны", new BigDecimal("17600.96"));
        Product Gucci = new Product(789L, "Сумка Gucci", "Вещи", new BigDecimal("202000.56"));
        Product Toyota = new Product(99L, "Toyota RAV4", "Автомобили", new BigDecimal("5_220_000.00"));
        Product Mercedes = new Product(17L, "Mercedes-Benz G-Класс", "Автомобили", new BigDecimal("35_800_000.00"));
        Product Porsche = new Product(18L, "Porsche 911 GT3 RS", "Автомобили", new BigDecimal("49_990_000.56"));
        Product lego = new Product(100L, "Конструктор LEGO", "Для детей", new BigDecimal("10_000"));
        Product Bicycle = new Product(101L, "Велосипед  AUTHOR Orbit", "Для детей", new BigDecimal("16_790"));
        Product BMW = new Product(13L, "BMW X6 30d", "Автомобили", new BigDecimal("17_100_000"));

        new Customer(1, "Ayna", 4, "оrders"), // Set<Order> orders я понимаю, что это так называемая корзина покупателя, но пока не совсем понимаю, как можно реализовать
                new Customer(2, "Sergei", 3, "12323"),
                new Customer(3, "Lesha", 1, ),
                new Customer(4, "Mama", 4, ),
                new Customer(5, "Danila", 2, )
                );
    }
}