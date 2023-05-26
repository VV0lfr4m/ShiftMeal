package com.example.demo.order;

import com.example.demo.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/inquireOngoingOrders")
    ResponseEntity<List<Order>> inquireOngoingOrders() {
        List<Order> orders = orderService.inquireOngoingOrders();

        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @PostMapping("/submitOrder")
    ResponseEntity<Order> submitOrder(@RequestBody Order order) {
        Order saved = orderService.submitOrder(order);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PostMapping("/placeOrder")
    ResponseEntity<Long> placeOrder(@RequestBody Order order) {
        Long id = orderService.placeOrder(order);

        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @PostMapping("/addToOrder")
    ResponseEntity<Order> addMealToOrder(@RequestParam Long mealId) {
        Order order = orderService.addMealToOrder(mealId);

        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @GetMapping("/reorder")
    ResponseEntity<Order> reorder(@RequestParam Long orderId) {
        Order order = orderService.reorder(orderId);

        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @GetMapping("/viewBasket")
    ResponseEntity<OrderInfo> viewBasket() {
        OrderInfo orderInfo = orderService.viewBasket();

        return ResponseEntity.status(HttpStatus.OK).body(orderInfo);
    }
}
