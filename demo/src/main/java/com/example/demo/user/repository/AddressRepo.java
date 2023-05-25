package com.example.demo.user.repository;

import com.example.demo.user.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {
    @Override
    Optional<Address> findById(Long id);
}
