package com.example.demo.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfo {
    private String placeName;
    private String deliveryOption;
    private List<Meal> mealList;
    private Pricing pricing;
    private Double courierTipAmount;
    private PaymentMethod paymentMethod;
}
