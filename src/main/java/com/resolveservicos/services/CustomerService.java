package com.resolveservicos.services;

import com.resolveservicos.entities.dto.CustomerRecord;
import com.resolveservicos.entities.model.Customer;
import com.resolveservicos.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public Customer createCustomer(CustomerRecord customerRecord) {
        Customer customer = new Customer();
        customer.setName(customerRecord.name());
        customer.setContactNumber(customerRecord.contactNumber());
        customer.setAddress(customerRecord.address());
        customer.setCreatedAt(LocalDate.now());

        customerRepository.save(customer);
        return customer;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void deleteCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer with id " + customerId + " not found"));

        customerRepository.delete(customer);
    }
}
