package org.example;

import javax.print.attribute.standard.OrientationRequested;
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

/*
CREATED     // только создан
SHIPPED     // передан в доставку
DELIVERED   // доставлен клиенту
CANCELLED   // отменён
 */
        Order order1 = new Order(1L, LocalDate.of(2021, 3, 15), LocalDate.of(2021, 3, 15), "CREATED", Set.of(apple, banana));
        Order order2 = new Order(2L, LocalDate.of(2021, 3, 15), LocalDate.of(2021, 3, 15), "DELIVERED", Set.of(bicycle, lego, book4));
        Order order3 = new Order(3L, LocalDate.of(2021, 2, 1), LocalDate.of(2021, 2, 26), "SHIPPED", Set.of(bmw));
        Order order4 = new Order(4L, LocalDate.of(2021, 1, 1), LocalDate.of(2021, 4, 1), "CANCELLED", Set.of(mercedes, porsche));
        Order order5 = new Order(5L, LocalDate.of(2021, 3, 15), LocalDate.of(2021, 3, 16), "SHIPPED", Set.of(toyota));
        Order order6 = new Order(6L, LocalDate.of(2021, 3, 14), LocalDate.of(2021, 3, 14), "SHIPPED", Set.of(gucci, iphone, cleaner));
        Order order7 = new Order(7L, LocalDate.of(2021, 2, 1), LocalDate.of(2021, 4, 2), "SHIPPED ", Set.of(book1, book2));
        Order order8 = new Order(8L, LocalDate.of(2021, 3, 14), LocalDate.of(2021, 3, 14), "DELIVERED", Set.of(spider, book3, football));

//1 — usual,2 — silver,3 — gold,4 — platinum

        Customer customer1 = new Customer(1L, "Айна", "platinum", Set.of(order6, order1));
        Customer customer2 = new Customer(2L, "Сергей", "gold", Set.of(order5, order7));
        Customer customer3 = new Customer(3L, "Лёша", "usual", Set.of(order2, order3));
        Customer customer4 = new Customer(4L, "Мама", "platinum", Set.of(order4));
        Customer customer5 = new Customer(5L, "Данила", "silver", Set.of(order8));
        List<Customer> customers = List.of(customer1, customer2, customer3, customer4, customer5);


        System.out.println("Задание 1. Получите список продуктов из категории Books с ценой более 100.");
        List<Product> expensiveBooks = customers.stream()// запускаем поток ПОКУПАТЕЛЕЙ (у меня их 5) я представил что у меня облако с 5ю покупателями
                .flatMap(c -> c.getOrders().stream())// "Дай мне свои заказы". разрываешь коробки с заказами и высыпаешь все заказы на стол.
                .flatMap(v -> v.getProducts().stream()) // "Заказ, покажи мне, какие в тебе продукты!" лежат все продукты из всех заказов всех покупателей.
                .filter(b -> b.getCategory().equalsIgnoreCase("books")).filter(p -> p.getPrice().compareTo(new BigDecimal("100")) > 0).toList();//"Собери все отфильтрованные продукты в список."
        System.out.println("\nCписок продуктов из категории Books с ценой более 100");
        expensiveBooks.forEach(System.out::println);


        System.out.println("\nЗадание 2 Получите список заказов с продуктами из категории Children's products");
        List<Order> ordersWithChildrenProducts = customers.stream().flatMap(c -> c.getOrders().stream())//ВСЕ ЗАКАЗЫ ПОКУПАТЕЛЕЙ НА СТОЛ, на столе только заказы
                .filter(order -> order.getProducts().stream()// теперь у нас стол, только из ЗАКАЗОВ
                        .anyMatch(b -> b.getCategory().equalsIgnoreCase("Children's products")))//теперь мы ищем, есть ли хотя бы один нужный продукт?
                .toList();// почему здесь компилятор ругается?
        System.out.println("\nCписок заказов с продуктами из категории Children's products");
        ordersWithChildrenProducts.forEach(System.out::println);


        System.out.println("\nЗадание 3. Получите список продуктов из категории Toys и примените скидку 10% и получите сумму всех продуктов.");
        BigDecimal totalToysPriceWithDiscount = customers.stream().flatMap(c -> c.getOrders().stream())//вытаскиваем все заказы на стол
                .flatMap(v -> v.getProducts().stream())// из заказов, выварачиваем все продукты, и кладем их на общий СТОЛ
                .filter(b -> b.getCategory().equalsIgnoreCase("Toys"))// убираем все лишние со стота, оставляем только товары из категории игрушки
                .map(n -> n.getPrice().multiply(new BigDecimal("0.9"))) //Метод multiply() используется у объектов типа BigDecimal. Он умножает одно число на другое.
                .reduce(BigDecimal.ZERO, BigDecimal::add);// помнится мне что reduce() - это терминальная операция, BigDecimal::add — это ссылка на метод add(), то есть операция сложения двух BigDecimal.
        List<Product> discountedToys = customers.stream().flatMap(c -> c.getOrders().stream())// кладем на стол все заказы
                .flatMap(v -> v.getProducts().stream())// вытаскиваем из заказов все продукты и кладем их снова на стол
                .filter(b -> b.getCategory().equalsIgnoreCase("Toys"))//ищем на столе продукты из категории Toys, и убираем все лишние со стола
                .map(p -> new Product(p.getId(), p.getName(), p.getCategory(), p.getPrice().multiply(new BigDecimal("0.9")))).toList();
        System.out.println("\nCписок продуктов из категории Toys.Применена скидка 10%");
        discountedToys.forEach(System.out::println);
        System.out.println("Сумма всех продуктов категории 'Toys' со скидкой 10%: " + totalToysPriceWithDiscount);

        System.out.println("\nЗадание 4.Получите список продуктов, заказанных клиентом второго уровня между 01-фев-2021 и 01-апр-2021.");
        List<Product> silverCustomerOrderedProducts = customers.stream()// на столе уже лежит Покупатели. Не нравится название customersThoTime, ментор будет ругаться
                .filter(f -> f.getLevel().equalsIgnoreCase("silver")) // и мы убираем со стола все лишние, и оставляет только покупателей с уровнем silver
                .flatMap(c -> c.getOrders().stream()) // теперь у покупателей выворачиваем все заказы и кладем их на стол
                .filter(order -> { // далее у заказов.
                    LocalDate date = order.getOrderDate();//Мы говорим что date - это дата Заказа
                    return (date.isAfter(LocalDate.of(2021, 1, 31)) && date.isBefore(LocalDate.of(2021, 4, 2))); // здесь мы создаем фильтр даты заказа, где ставим период с 01-фев-2021 и 01-апр-2021.
                }) // isAfter - начало периода, isBefore - конец периода, && - это условие, будем простым языком говорить, что это дефис
                .flatMap(g -> g.getProducts().stream())// у отфильтрованных наших заказов, выворачиваем все продукты.
                .toList();
        System.out.println("\nСписок продуктов, заказанных клиентом второго уровня между 01-фев-2021 и 01-апр-2021.");
        silverCustomerOrderedProducts.forEach(System.out::println);

        System.out.println("\nЗадание 5.Получите топ 2 самые дешевые продукты из категории Books.");
        /* Мысли вслух, прокачиваем логические мышление в программирование
        мне кажется нужно работать с методами limit(), и filter()
        но еще нужно подумать над условием, как объяснить компилятору, что нужно из всего списка категории Books, выбрать две самые дешевые
         может быть отсортируем полученный список, по  BigDecimal price, здесь нужно еще подумать как это сделать, понимаю что нужно работать по геттеру
         и после поставим limit (2), это не выход???
         */
        List<Product> topTwoCheapBooks = customers.stream().flatMap(c -> c.getOrders().stream()).flatMap(v -> v.getProducts().stream()).filter(b -> b.getCategory().equalsIgnoreCase("Books")).sorted(Comparator.comparing((product -> product.getPrice())))
                /*
                Comparator.comparing(...) — инструмент сортировки по конкретному полю.
                Product::getPrice — говорит, по какому полю сортировать.
                Без reversed() — по возрастанию.
                С reversed() — по убыванию.
                 */.limit(2).toList();
        System.out.println("\nДва самых дешевых товара из категории Books.");
        topTwoCheapBooks.forEach(System.out::println);


        System.out.println("\nЗадание 7. Получите список заказов, сделанных 15-марта-2021, выведите id заказов в консоль и затем верните список их продуктов.");
/*
Мысли вслух: работаем с двумя стримами ,первый стрим выводит id заказов, второй стрим выводит список этих продуктов
 в первом случае работаем с методом filter(), для того чтобы выставить период
*/
        List<Order> orders15march = customers.stream() // перед столом сидят покупателя
                .flatMap(c -> c.getOrders().stream()) //мы и говорим, кладите на стол все ваши заказы
                .filter(order -> order.getOrderDate().isEqual(LocalDate.of(2021, 3, 15))).toList();
        System.out.println("ID заказов, сделанных 15 марта 2021:");
        orders15march.stream().map(Order::getId) // можно через лямбду .map(order -> order.getId())
                .forEach(System.out::println);
        List<Product> productsFromOrders15March = orders15march.stream().flatMap(c -> c.getProducts().stream()).toList();
        System.out.println("Продукты из заказов 15 марта 2021:");
        productsFromOrders15March.forEach(System.out::println);

        System.out.println("\nЗадание 8. Рассчитайте общую сумму всех заказов, сделанных в феврале 2021.");
        /*
        Мысли вслух. Сперва ищем через  заказы
        filter(order -> { // далее у заказов.
                    LocalDate date = order.getOrderDate();//Мы говорим что date - это дата Заказа
                    return (date.isAfter(LocalDate.of(2021, 1, 31)) && date.isBefore(LocalDate.of(2021, 3, 1)));
                     Дальше найти список продуктов, - это через flatMap()
                     Дальше нужно обратиться к геттуру price
                     и с помощью метода reduce()
                     .reduce(BigDecimal.ZERO, BigDecimal::add) - получится сумму
         */
        BigDecimal totalOrderSumFeb2021 = customers.stream().flatMap(c -> c.getOrders().stream()).filter(order -> {
                    LocalDate date = order.getOrderDate();//Мы говорим что date - это дата Заказа
                    return (date.isAfter(LocalDate.of(2021, 1, 31)) && date.isBefore(LocalDate.of(2021, 3, 1)));
                }).flatMap(v -> v.getProducts()
                        .stream()).map(product -> product.getPrice())
                .reduce(BigDecimal.ZERO, (a, b) -> a.add(b)); // или .reduce(BigDecimal.ZERO, BigDecimal::add) - это тоже самое
        System.out.println("Сумма всех заказов, сделанных в феврале 2021 составляет " + totalOrderSumFeb2021 + "руб");

        System.out.println("\nЗадание 9. Рассчитайте средний платеж по заказам, сделанным 14-марта-2021.");
        /*
        Мысли вслух: сперва фильтруем заказы с помощью filter()
        формируем список продуктов этого числа
        После надо спросить у gpt, есть ли метод определяющий среднее значение price из  всего списка , которые мы ранее нашли
         */
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
            /*
            что делает метод divide() - делим
  BigDecimal.valueOf(count) — количество элементов, тоже как BigDecimal
  BigDecimal.valueOf(3); // станет BigDecimal  Потому что нельзя просто делить BigDecimal / int. Нужно, чтобы оба операнда были BigDecimal.
RoundingMode.HALF_UP — способ округления
            prices.size()) - размер, количество элементов в prices стриме
2 — округляем результат до 2 знаков после запятой
             */
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
            BigDecimal minBook = bookPrices.stream().min(Comparator.naturalOrder()) // найти минимальный элемент в стриме, используя естественный порядок.встроенный компаратор, который сравнивает элементы по их естественному порядку.
                    .orElse(BigDecimal.ZERO); // "Найди минимальную цену. Если список пустой — верни 0."
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
        Map<Long, Integer> keyIdVal = customers.stream() // создаем Map библиотеку в которой тип Long это key, а Integer количество
                .flatMap(c -> c.getOrders().stream())
                .collect(Collectors.toMap(order -> order.getId(),// собираем все в библиотеку Map с помощью Collectors.toMap, где первое значение order мы обозначение ID order -> order.getId(), можно через так Order::getId
                        order -> order.getProducts().size()));//а во втором количество заказов с помощью  order -> order.getProducts().size()
        keyIdVal.forEach((id, count) ->
        /* Ты просто перебираешь содержимое этой Map, где:
id — это ключ (Long)
count — это значение (Integer)
keyIdVal.forEach((k, v) ->
    System.out.println("Заказ №" + k + " содержит " + v + " товар(а/ов)."));
         */
                System.out.println("Заказ №" + id + " содержит " + count + " товар(а/ов)."));

        System.out.println("\nЗадание 12. Создайте Map<Customer, List<Order>> → key - покупатель, value - список его заказов");
        Map<Customer, List<Order>> customerListOrders = customers.stream().
                collect(Collectors.toMap(customer -> customer,
                        customer -> new ArrayList<>(customer.getOrders())));
        customerListOrders.forEach((customer, orders) -> {
            String orderIds = orders.stream() // создаем отдельный стрим,в котором мы будем только отображать номер заказа
                    .map(order -> String.valueOf(order.getId()))// «Возьми order.getId() (тип Long) и сделай из него String, чтобы можно было потом собрать в одну строку».
                    .collect(Collectors.joining(", ")); // Объединяет элементы в одну строку, разделяя их заданным текстом — в данном случае запятой и пробелом.
            System.out.println("Имя покупателя: " + customer.getName() + ". Его заказы: " + orderIds);
        });

        System.out.println("\nЗадание 13. Создайте Map<Order, Double> → key - заказ, value - общая сумма продуктов заказа.");
        Map<Order, Double> orderProducts = customers.stream()// перед столом покупатели, так как нам нужно добраться до заказов,
                .flatMap(c -> c.getOrders().stream())//мы говорим выворачивайте все заказы

                .collect(Collectors.toMap(order -> order, // далее собираем из них map библиотеку
                        order -> order.getProducts().stream()// для value, нам нужно обратиться к стоимости продуктов, поэтому выворачиваем продукты
                                .map(Product::getPrice) // создаем список цен продуков
                                .reduce(BigDecimal.ZERO, BigDecimal::add) // складываем с помощью add()
                                .setScale(2, RoundingMode.HALF_UP) // я не знаю этот метод setScale(), но я предполагаю, что это метод округления
                                /*
                                кстати и округления не работают
                                Заказ №2 — сумма продуктов: 26840.99
Заказ №5 — сумма продуктов: 5220000.0
Заказ №8 — сумма продуктов: 2598.99
Заказ №6 — сумма продуктов: 229600.52
Заказ №4 — сумма продуктов: 8.579000056E7
Заказ №7 — сумма продуктов: 4416.3
Заказ №3 — сумма продуктов: 1.71E7
Заказ №1 — сумма продуктов: 375.12
                                 */
                                .doubleValue() // преобразуем BigDecimal в Double
                ));
        orderProducts.forEach((order, sum) ->
                System.out.printf("Заказ №%d — сумма продуктов: %.2f%n", order.getId(), sum));
        /*
        № — обычный символ, который будет выведен как есть (например: №1, №2 и т.д.)
%d — спецификатор формата для целого числа (integer)

%.2f
%f — спецификатор формата для числа с плавающей точкой (float/double)
.2 — означает 2 знака после запятой
         */

        System.out.println("\nЗадание 14. Получите Map<String, List<String>> → key - категория, value - список названий товаров в категории");
        Map<String, List<String>> listOfProductsByCategory = customers.stream()
                .flatMap(c -> c.getOrders().stream()) // сперва выворачиваем все заказы
                .flatMap(v -> v.getProducts().stream())// после выворачиваем все продукты, на столе только продукты
                .collect(Collectors.groupingBy( //  "Раздели все продукты на группы по их категории"
                        Product::getCategory,//Это функция группировки — она говорит, что использовать в качестве ключа для Map.
                        Collectors.mapping(Product::getName, Collectors.toList())));//что именно мы хотим положить в список (значение Map).

        listOfProductsByCategory.forEach((category, products) -> {
            System.out.println("\nКатегория " + category);
            System.out.println("Товары: " + String.join("| ", products));//"Из каждого Product возьми только его имя (getName()), и положи в список".
        });

        System.out.println("\nЗадание 15. Получите Map<String, Product> → самый дорогой продукт по каждой категории.");
        /*
        Мысли вслух. Сперва мы должны дойти до момента когда на столе, только продукты
        Далее, на большом столе с помощью Collectors.groupingBy, делим продукты на категории
        где в качестве ключа мы используем категорию  Product::getCategory
        Далее мы должны в value положить самой дорогой продукт из категории
        очень сильно напрашивается метод сортировки, где мы берем последний элемент по цене, и берем только название,
        но можно еще через reduce, наверное сделать, где вызвать max. Короче я поплыл здесь
        я напишу код по аналогии как мы делали ранее, а как вызвать максимальный продукт из категории я не справлюсь
        */
        Map<String, Product> ExpensiveProductCategory = customers.stream()
                .flatMap(c -> c.getOrders().stream())
                .flatMap(v -> v.getProducts().stream())
                .collect(Collectors.groupingBy(
                        Product::getCategory,// до сюда я написал сам
                        Collectors.collectingAndThen( // что делает collectingAndThen
                                Collectors.maxBy(Comparator.comparing(Product::getPrice)), // Collectors.maxBy(...) — ищем в каждой категории продукт с максимальной ценой.
                                Optional::get // get, отдает значений , это фишка Optional
                        )
                ));
        ExpensiveProductCategory.forEach((category,product)->
                System.out.println("Категория: " + category + " → Самый дорогой товар: " + product.getName() + " (Цена: " + product.getPrice() + " руб.)"));

    }
}