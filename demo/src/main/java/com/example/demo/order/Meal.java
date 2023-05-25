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
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String name;
    private byte[] image;
    private String description;
    private Double price;
    private Double rating;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Addon> addons;
    private Integer amount;
}
