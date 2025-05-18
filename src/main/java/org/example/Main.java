package org.example;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.math.RoundingMode;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Product apple = new Product(1L, "Яблоко", "fruits", new BigDecimal("204.56"));
        Product banana = new Product(2L, "Бананы", "fruits", new BigDecimal("170.56"));
        Product cleaner = new Product(3L, "Пылесос SAMSUNG", "Appliances", new BigDecimal("9999.00"));
        Product iphone = new Product(4L, "IPHONE APPLE 14", "phone", new BigDecimal("17600.96"));
        Product gucci = new Product(5L, "Сумка gucci", "Things", new BigDecimal("202000.56"));
        Product toyota = new Product(6L, "toyota RAV4", "Car", new BigDecimal("5220000.00"));
        Product mercedes = new Product(7L, "mercedes-Benz G-Класс", "Car", new BigDecimal("35800000.00"));
        Product porsche = new Product(8L, "porsche 911 GT3 RS", "Car", new BigDecimal("49990000.56"));
        Product lego = new Product(9L, "Конструктор LEGO", "Toys", new BigDecimal("10000"));
        Product bicycle = new Product(10L, "Велосипед  AUTHOR Orbit", "Children's products", new BigDecimal("16790"));
        Product football = new Product(11L, "Футбольный мяч", "Children's products", new BigDecimal("999"));
        Product bmw = new Product(12L, "bmw X6 30d", "Car", new BigDecimal("17100000"));
        Product book1 = new Product(100L, "Война и Мир", "books", new BigDecimal("765.80"));
        Product book2 = new Product(101L, "Гарри Поттер", "books", new BigDecimal("3650.50"));
        Product book3 = new Product(102L, "Детские сказки", "books", new BigDecimal("99.99"));
        Product book4 = new Product(103L, "Детские стихи", "books", new BigDecimal("50.99"));
        Product spider = new Product(110L, "Детский Человек-Паук", "Toys", new BigDecimal("1500"));

        Order order1 = new Order(1L, LocalDate.of(2021, 3, 15), LocalDate.of(2021, 3, 15), "CREATED", Set.of(apple, banana));
        Order order2 = new Order(2L, LocalDate.of(2021, 3, 15), LocalDate.of(2021, 3, 15), "DELIVERED", Set.of(bicycle, lego, book4));
        Order order3 = new Order(3L, LocalDate.of(2021, 2, 1), LocalDate.of(2021, 2, 26), "SHIPPED", Set.of(bmw));
        Order order4 = new Order(4L, LocalDate.of(2021, 1, 1), LocalDate.of(2021, 4, 1), "CANCELLED", Set.of(mercedes, porsche));
        Order order5 = new Order(5L, LocalDate.of(2021, 3, 15), LocalDate.of(2021, 3, 16), "SHIPPED", Set.of(toyota));
        Order order6 = new Order(6L, LocalDate.of(2021, 3, 14), LocalDate.of(2021, 3, 14), "SHIPPED", Set.of(gucci, iphone, cleaner));
        Order order7 = new Order(7L, LocalDate.of(2021, 2, 1), LocalDate.of(2021, 4, 2), "SHIPPED ", Set.of(book1, book2));
        Order order8 = new Order(8L, LocalDate.of(2021, 3, 14), LocalDate.of(2021, 3, 14), "DELIVERED", Set.of(spider, book3, football));

        Customer customer1 = new Customer(1L, "Айна", "platinum", Set.of(order6, order1));
        Customer customer2 = new Customer(2L, "Сергей", "gold", Set.of(order5, order7));
        Customer customer3 = new Customer(3L, "Лёша", "usual", Set.of(order2, order3));
        Customer customer4 = new Customer(4L, "Мама", "platinum", Set.of(order4));
        Customer customer5 = new Customer(5L, "Данила", "silver", Set.of(order8));
        List<Customer> customers = List.of(customer1, customer2, customer3, customer4, customer5);

        System.out.println("Задание 1. Получите список продуктов из категории Books с ценой более 100.");
        List<Product> expensiveBooks = customers.stream()
                .flatMap(c -> c.getOrders().stream())
                .flatMap(v -> v.getProducts().stream())
                .filter(b -> b.getCategory().equalsIgnoreCase("books")).filter(p -> p.getPrice().compareTo(new BigDecimal("100")) > 0).toList();
        System.out.println("\nCписок продуктов из категории Books с ценой более 100");
        expensiveBooks.forEach(System.out::println);

        System.out.println("\nЗадание 2 Получите список заказов с продуктами из категории Children's products");
        List<Order> ordersWithChildrenProducts = customers.stream().flatMap(c -> c.getOrders().stream())
                .filter(order -> order.getProducts().stream()
                        .anyMatch(b -> b.getCategory().equalsIgnoreCase("Children's products")))
                .toList();
        System.out.println("\nCписок заказов с продуктами из категории Children's products");
        ordersWithChildrenProducts.forEach(System.out::println);


        System.out.println("\nЗадание 3. Получите список продуктов из категории Toys и примените скидку 10% и получите сумму всех продуктов.");
        BigDecimal totalToysPriceWithDiscount = customers.stream().flatMap(c -> c.getOrders().stream())
                .flatMap(v -> v.getProducts().stream())
                .filter(b -> b.getCategory().equalsIgnoreCase("Toys"))
                .map(n -> n.getPrice().multiply(new BigDecimal("0.9")))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        List<Product> discountedToys = customers.stream().flatMap(c -> c.getOrders().stream())
                .flatMap(v -> v.getProducts().stream())
                .filter(b -> b.getCategory().equalsIgnoreCase("Toys"))
                .map(p -> new Product(p.getId(), p.getName(), p.getCategory(), p.getPrice().multiply(new BigDecimal("0.9")))).toList();
        System.out.println("\nCписок продуктов из категории Toys.Применена скидка 10%");
        discountedToys.forEach(System.out::println);
        System.out.println("Сумма всех продуктов категории 'Toys' со скидкой 10%: " + totalToysPriceWithDiscount);

        System.out.println("\nЗадание 4.Получите список продуктов, заказанных клиентом второго уровня между 01-фев-2021 и 01-апр-2021.");
        List<Product> silverCustomerOrderedProducts = customers.stream()
                .filter(f -> f.getLevel().equalsIgnoreCase("silver"))
                .flatMap(c -> c.getOrders().stream())
                .filter(order -> {
                    LocalDate date = order.getOrderDate();
                    return (date.isAfter(LocalDate.of(2021, 1, 31)) && date.isBefore(LocalDate.of(2021, 4, 2)));
                })
                .flatMap(g -> g.getProducts().stream())
                .toList();
        System.out.println("\nСписок продуктов, заказанных клиентом второго уровня между 01-фев-2021 и 01-апр-2021.");
        silverCustomerOrderedProducts.forEach(System.out::println);

        System.out.println("\nЗадание 5.Получите топ 2 самые дешевые продукты из категории Books.");

        List<Product> topTwoCheapBooks = customers.stream()
                .flatMap(c -> c.getOrders().stream()).flatMap(v -> v.getProducts().stream())
                .filter(b -> b.getCategory().equalsIgnoreCase("Books"))
                .sorted(Comparator.comparing((product -> product.getPrice())))
                .limit(2).toList();
        System.out.println("\nДва самых дешевых товара из категории Books.");
        topTwoCheapBooks.forEach(System.out::println);


        System.out.println("\nЗадание 7. Получите список заказов, сделанных 15-марта-2021, выведите id заказов в консоль и затем верните список их продуктов.");

        List<Order> orders15march = customers.stream()
                .flatMap(c -> c.getOrders().stream())
                .filter(order -> order.getOrderDate().isEqual(LocalDate.of(2021, 3, 15))).toList();
        System.out.println("ID заказов, сделанных 15 марта 2021:");
        orders15march.stream().map(Order::getId)
                .forEach(System.out::println);
        List<Product> productsFromOrders15March = orders15march.stream().flatMap(c -> c.getProducts().stream()).toList();
        System.out.println("Продукты из заказов 15 марта 2021:");
        productsFromOrders15March.forEach(System.out::println);

        System.out.println("\nЗадание 8. Рассчитайте общую сумму всех заказов, сделанных в феврале 2021.");
        BigDecimal totalOrderSumFeb2021 = customers.stream().flatMap(c -> c.getOrders().stream()).filter(order -> {
                    LocalDate date = order.getOrderDate();
                    return (date.isAfter(LocalDate.of(2021, 1, 31)) && date.isBefore(LocalDate.of(2021, 3, 1)));
                }).flatMap(v -> v.getProducts()
                        .stream()).map(product -> product.getPrice())
                .reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
        System.out.println("Сумма всех заказов, сделанных в феврале 2021 составляет " + totalOrderSumFeb2021 + "руб");

        System.out.println("\nЗадание 9. Рассчитайте средний платеж по заказам, сделанным 14-марта-2021.");
        List<BigDecimal> prices = customers.stream()
                .flatMap(c -> c.getOrders().stream())
                .filter(order -> order.getOrderDate().isEqual(LocalDate.of(2021, 3, 14)))
                .flatMap(v -> v.getProducts().stream())
                .map(Product::getPrice).toList();
        BigDecimal averagePayment = BigDecimal.ZERO;
        if (!prices.isEmpty()) {
            BigDecimal total = prices.stream()
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            averagePayment = total.divide(BigDecimal.valueOf(prices.size()), 2, RoundingMode.HALF_UP);

        }
        System.out.println("Средний платеж по заказам, сделанных  14-марта-2021. составляет " + averagePayment + "руб");

        System.out.println("\nЗадание 10. Получите набор статистических данных (сумма, среднее, максимум, минимум, количество) для всех продуктов категории Книги.");
        List<BigDecimal> bookPrices = customers.stream().
                flatMap(c -> c.getOrders().stream())
                .flatMap(v -> v.getProducts().stream())
                .filter(b -> b.getCategory().equalsIgnoreCase("Books"))
                .map(Product::getPrice).toList();
        BigDecimal averageCostBook = BigDecimal.ZERO;
        if (!prices.isEmpty()) {
            BigDecimal sumBook = bookPrices.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
            averageCostBook = sumBook.divide(BigDecimal.valueOf(bookPrices.size()), 2, RoundingMode.HALF_UP);
            BigDecimal minBook = bookPrices.stream().min(Comparator.naturalOrder())
                    .orElse(BigDecimal.ZERO);
            BigDecimal maxBook = bookPrices.stream().max(Comparator.naturalOrder()).orElse(BigDecimal.ZERO);
            long countBook = bookPrices.size();
            System.out.println("Сумма стоимости товаров из категории 'Books', составляет - " + sumBook + " руб.");
            System.out.println("Средняя стоимость товара из категории 'Books', составляет - " + averageCostBook + " руб.");
            System.out.println("Минимальная стоимость товара из категории 'Books', составляет - " + minBook + " руб.");
            System.out.println("Максимальная стоимость товара из категории 'Books', составляет - " + maxBook + " руб.");
            System.out.println("Количество товаров из категории 'Books', составляет: " + countBook);
        } else {
            System.out.println("Нет товаров категории 'Books'.");
        }

        System.out.println("\nЗадание 11. Получите данные Map<Long, Integer> → key - id заказа, value - кол-во товаров в заказе");
        Map<Long, Integer> keyIdVal = customers.stream()
                .flatMap(c -> c.getOrders().stream())
                .collect(Collectors.toMap(order -> order.getId(),
                        order -> order.getProducts().size()));
        keyIdVal.forEach((id, count) ->
                System.out.println("Заказ №" + id + " содержит " + count + " товар(а/ов)."));

        System.out.println("\nЗадание 12. Создайте Map<Customer, List<Order>> → key - покупатель, value - список его заказов");
        Map<Customer, List<Order>> customerListOrders = customers.stream().
                collect(Collectors.toMap(customer -> customer,
                        customer -> new ArrayList<>(customer.getOrders())));
        customerListOrders.forEach((customer, orders) -> {
            String orderIds = orders.stream()
                    .map(order -> String.valueOf(order.getId()))
                    .collect(Collectors.joining(", "));
            System.out.println("Имя покупателя: " + customer.getName() + ". Его заказы: " + orderIds);
        });

        System.out.println("\nЗадание 13. Создайте Map<Order, Double> → key - заказ, value - общая сумма продуктов заказа.");
        Map<Order, Double> orderProducts = customers.stream()
                .flatMap(c -> c.getOrders().stream())

                .collect(Collectors.toMap(order -> order,
                        order -> order.getProducts().stream()
                                .map(Product::getPrice)
                                .reduce(BigDecimal.ZERO, BigDecimal::add)
                                .setScale(2, RoundingMode.HALF_UP)
                                .doubleValue()
                ));
        orderProducts.forEach((order, sum) ->
                System.out.printf("Заказ №%d — сумма продуктов: %.2f%n", order.getId(), sum));

        System.out.println("\nЗадание 14. Получите Map<String, List<String>> → key - категория, value - список названий товаров в категории");
        Map<String, List<String>> listOfProductsByCategory = customers.stream()
                .flatMap(c -> c.getOrders().stream())
                .flatMap(v -> v.getProducts().stream())
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.mapping(Product::getName, Collectors.toList())));
        listOfProductsByCategory.forEach((category, products) -> {
            System.out.println("\nКатегория " + category);
            System.out.println("Товары: " + String.join("| ", products));
        });

        System.out.println("\nЗадание 15. Получите Map<String, Product> → самый дорогой продукт по каждой категории.");
        Map<String, Product> ExpensiveProductCategory = customers.stream()
                .flatMap(c -> c.getOrders().stream())
                .flatMap(v -> v.getProducts().stream())
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparing(Product::getPrice)),
                                Optional::get
                        )
                ));
        ExpensiveProductCategory.forEach((category, product) ->
                System.out.println("Категория: " + category + " → Самый дорогой товар: " + product.getName() + " (Цена: " + product.getPrice() + " руб.)"));

    }
}