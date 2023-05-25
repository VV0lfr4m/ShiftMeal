package com.example.demo.order.repository;

import com.example.demo.order.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    @Query("select p from Promotion p where p.isActive = true")
    List<Promotion> findOngoingPromotions();
}
