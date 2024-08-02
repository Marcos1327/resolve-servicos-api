package com.resolveservicos.repositories;

import com.resolveservicos.entities.model.Customer;
import com.resolveservicos.entities.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional <Customer> findByNameAndUser(String name, User userLogged);

    List<Customer> findAllByUser(User userLogged);

    Optional<Customer> findByCustomerIdAndUser(Long customerId, User userLogged);

}
