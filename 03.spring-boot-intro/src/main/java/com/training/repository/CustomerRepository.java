package com.training.repository;

import com.training.entity.Address;
import com.training.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    public Optional<Customer> findByEmail(String email);
}
