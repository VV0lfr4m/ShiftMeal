package com.example.demo.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double rating;
    private Long addressId;
    private String additionalInfo;
    private Long deliveryId;
    @OneToMany(targetEntity = Meal.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Meal> meals;
    private String category;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<WorkTime> workTime;
}
