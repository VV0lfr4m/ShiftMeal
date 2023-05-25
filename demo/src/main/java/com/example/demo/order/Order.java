package com.example.demo.order;

import com.example.demo.user.entity.Address;

import java.util.Date;
import java.util.List;

public class Order {
    private Long id;
    private String placeName;
    private Date deliveryDate;
    private List<Meal> mealList;
    private Pricing pricing;
    private Address deliveryAddress;
}
