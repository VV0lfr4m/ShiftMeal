package com.example.demo.order.repository;

import com.example.demo.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserIdAndStatusAndIsActive(Long userId, String status, boolean isActive);
}
