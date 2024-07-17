package com.resolveservicos.services;

import com.resolveservicos.entities.dto.CustomerRecord;
import com.resolveservicos.entities.model.Customer;
import com.resolveservicos.handlers.ResourceNotFoundException;
import com.resolveservicos.repositories.CustomerRepository;
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

    public Customer getCustomerByName (String name) {
        Customer customer = customerRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Customer not found with name: " + name));
        return customer;
    }

    public Customer updateCustomer(Long customerId, CustomerRecord customerRecord) {
        Customer customer = findById(customerId);

        if (customerRecord.name() != null) {
            customer.setName(customerRecord.name());
        }
        if (customerRecord.contactNumber() != null) {
            customer.setContactNumber(customerRecord.contactNumber());
        }
        if (customerRecord.address() != null) {
            customer.setAddress(customerRecord.address());
        }

        customerRepository.save(customer);
        return customer;
    }

    private Customer findById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void deleteCustomer(Long customerId) {
        Customer customer = findById(customerId);

        customerRepository.delete(customer);
    }
}
