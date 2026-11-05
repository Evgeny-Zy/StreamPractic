package com.example.stream;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamMethods {

    // Пункт 2.
    public static Map<String, Long> getGroupingOrders(List<Order> list) {
        return list.stream()
                .collect(Collectors.groupingBy(Order::getProduct, Collectors.counting()));
    }

    // Пункт 3.
    public static Map<String, Double> getGroupingOrdersAndTotalCost(List<Order> list) {
        return list.stream()
                .collect(Collectors.toMap(Order::getProduct, Order::getCost, Double::sum));
    }

    // Пункт 4.
    public static Map<String, Double> sortOrdersAtTotalCost(List<Order> list) {
        Map<String, Double> map = getGroupingOrdersAndTotalCost(list);
        return map.entrySet().stream()
                .sorted(Comparator.comparingDouble(Map.Entry<String, Double>::getValue).reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (m, n) -> m, LinkedHashMap::new)
                );
    }

    // Пункт 5.
    public static List<Order> getTheeExpensiveOrder(List<Order> list) {
        return list.stream()
                .sorted(Comparator.comparingDouble(Order::getCost).reversed())
                .limit(3)
                .toList();
    }

    // Пункт 6.
    public static void printTheeExpensiveOrderAndTotal(List<Order> list) {
        List<Order> orders = getTheeExpensiveOrder(list);
        double totalCost = 0.0;
        for (Order order : orders) {
            System.out.println(order);
            totalCost += order.getCost();
        }
        System.out.println("Общая стоимость: " + totalCost);
    }
}

