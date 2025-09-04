package com.dtheof.reservation_system.repo;

import com.dtheof.reservation_system.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    void deleteCustomerByEmail(String email);
   Optional<Customer> findCustomerByEmail(String email);
}
