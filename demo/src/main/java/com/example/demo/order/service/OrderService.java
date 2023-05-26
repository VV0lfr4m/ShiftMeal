package com.example.demo.order.service;

import com.example.demo.order.Order;
import com.example.demo.order.OrderInfo;

import java.util.List;

public interface OrderService {
    List<Order> inquireOngoingOrders();

    Long placeOrder(Order order);

    Order addMealToOrder(Long mealId);

    Order reorder(Long orderId);

    Order submitOrder(Order order);

    OrderInfo viewBasket();
}
