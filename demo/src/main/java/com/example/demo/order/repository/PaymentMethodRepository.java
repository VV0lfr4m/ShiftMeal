package com.example.demo.order.repository;

import com.example.demo.order.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod , Long> {

    //todo change query
    @Query("select pm from PaymentMethod pm")
    PaymentMethod findPrefered(Long userId);
}
