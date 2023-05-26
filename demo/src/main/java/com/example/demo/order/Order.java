package com.example.demo.order;

import com.example.demo.user.entity.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String placeName;
    @OneToMany
    private List<Meal> mealList;
    @OneToOne
    private Pricing pricing;
    private Date deliveryDate;
    @OneToOne
    private Address deliveryAddress;
    private Double price;
    private Long userId;
    private String status;
    private boolean isActive;
}
