package com.example.demo.order.service;

import com.example.demo.Utils;
import com.example.demo.order.*;
import com.example.demo.order.repository.MealRepository;
import com.example.demo.order.repository.OrderRepository;
import com.example.demo.order.repository.PaymentMethodRepository;
import com.example.demo.user.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private static final String PENDING = "pending";
    private static final String SET_UP = "set-up";
    private static final boolean DEFAULT_TRUE_FLAG = true;
    private static final String ORDER_WITH_STATUS_S_NOT_FOUND_FOR_USER_D = "Order with status %s not found for user %d.";
    private static final String MEAL_NOT_FOUND_WITH_ID_D = "Meal not found with id %d";
    private static final String ORDER_NOT_FOUND_WITH_ID_D = "Order not found with id %d";
    public static final String DELIVERY = "delivery";

    private final OrderRepository orderRepository;
    private final Utils utils;
    private final MealRepository mealRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    public OrderServiceImpl(OrderRepository orderRepository, Utils utils, MealRepository mealRepository, PaymentMethodRepository paymentMethodRepository) {
        this.orderRepository = orderRepository;
        this.utils = utils;
        this.mealRepository = mealRepository;
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> inquireOngoingOrders() {
        Long userId = utils.inquireLoggedInUserId();

        return orderRepository.findByUserIdAndStatusAndIsActive(userId, PENDING, DEFAULT_TRUE_FLAG);
    }

    @Override
    @Transactional
    public Long placeOrder(Order order) {
       return orderRepository.save(order).getId();
    }

    @Override
    @Transactional
    public Order addMealToOrder(Long mealId) {
        Long userId = utils.inquireLoggedInUserId();
        Order order = orderRepository.findByUserIdAndStatusAndIsActive(userId, SET_UP, DEFAULT_TRUE_FLAG).stream()
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format(ORDER_WITH_STATUS_S_NOT_FOUND_FOR_USER_D, SET_UP, userId)));

        Meal meal = mealRepository.findById(mealId).orElseThrow(() -> new NotFoundException(String.format(MEAL_NOT_FOUND_WITH_ID_D, mealId)));
        order.getMealList().add(meal);

        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order reorder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException(String.format(ORDER_NOT_FOUND_WITH_ID_D, orderId)));
        order.setId(null);

        return submitOrder(order);
    }

    @Override
    @Transactional
    public Order submitOrder(Order order) {
        Order saved = orderRepository.save(order);
        //todo call payment api

        return saved;
    }

    @Override
    @Transactional(readOnly = true)
    public OrderInfo viewBasket() {
        Long userId = utils.inquireLoggedInUserId();
        Order order = orderRepository.findByUserIdAndStatusAndIsActive(userId, SET_UP, DEFAULT_TRUE_FLAG).stream()
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format(ORDER_WITH_STATUS_S_NOT_FOUND_FOR_USER_D, SET_UP, userId)));

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setMealList(order.getMealList());
        orderInfo.setPlaceName(order.getPlaceName());

        PaymentMethod paymentMethod = paymentMethodRepository.findPrefered(userId);
        orderInfo.setPaymentMethod(paymentMethod);
        orderInfo.setPricing(calculatePricing(order));
        orderInfo.setDeliveryOption(DELIVERY);
        orderInfo.setCourierTipAmount(0.0);

        return orderInfo;
    }

    private Pricing calculatePricing(Order order) {
        Pricing pricing = new Pricing();
        // call external delivery calculation api
        Double deliveryFee = 50.0;
        Integer deliveryDiscount = 10;
        Integer discount = 10;
        pricing.setDeliveryFee(deliveryFee);
        pricing.setDeliveryDiscount(deliveryDiscount);
        pricing.setDiscount(discount);

        Double mealSum = order.getMealList().stream().mapToDouble(Meal::getPrice).sum();
        Double mealDiscountAmount = mealSum * (discount/100);
        Double mealPrice = mealSum - mealDiscountAmount;
        Double discountAmount = deliveryFee * (deliveryDiscount/100);
        Double deliveryPrice = deliveryFee - discountAmount;
        Double total = mealPrice + deliveryPrice;
        pricing.setTotal(total);

        return pricing;
    }
}
