package com.resolveservicos.repositories;

import com.resolveservicos.entities.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
