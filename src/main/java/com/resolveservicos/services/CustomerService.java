package com.resolveservicos.services;

import com.resolveservicos.entities.dto.CustomerRecord;
import com.resolveservicos.entities.model.Customer;
import com.resolveservicos.entities.model.User;
import com.resolveservicos.handlers.BusinessException;
import com.resolveservicos.handlers.ResourceNotFoundException;
import com.resolveservicos.repositories.CustomerRepository;
import com.resolveservicos.utils.Util;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UserService userService;
    private final Util util;

    public CustomerService(CustomerRepository customerRepository, UserService userService, Util util) {
        this.customerRepository = customerRepository;
        this.userService = userService;
        this.util = util;
    }


    public Customer createCustomer(CustomerRecord customerRecord) {
        Customer customer = new Customer();

        User user = userService.getLoggedUser();

        if (user == null) {
            throw new ResourceNotFoundException("User not found with id: " + user.getUserId());
        }

        if (util.isNullOrEmpty(customerRecord.name()) || util.isNullOrEmpty(customerRecord.contactNumber()) || util.isNullOrEmpty(customerRecord.address())) {
            throw new ResourceNotFoundException("All fields are required!");
        }
        customer.setName(customerRecord.name());
        customer.setContactNumber(customerRecord.contactNumber());
        customer.setAddress(customerRecord.address());
        customer.setCreatedAt(LocalDate.now());
        customer.setUser(user);

        customerRepository.save(customer);
        return customer;
    }

    public Customer getCustomerById(Long customerId) {
        Customer customer = findById(customerId);
        return customer;
    }

    public Customer getCustomerByName (String name) {
        User userLogged = userService.getLoggedUser();
        Customer customer = customerRepository.findByNameAndUser(name, userLogged).orElseThrow(() -> new ResourceNotFoundException("Customer not found with name: " + name));
        return customer;
    }

    public Customer updateCustomer(Long customerId, CustomerRecord customerRecord) {
        Customer customer = findById(customerId);
        User userLogged = userService.getLoggedUser();

        if (userLogged.getUserId() != null) {
            customer.setUser(userLogged);
            if (userLogged == null) {
                throw new ResourceNotFoundException("User not found with id: " + userLogged.getUserId());
            }
        }

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
        User userLogged = userService.getLoggedUser();
        return customerRepository.findByCustomerIdAndUser(customerId, userLogged)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));

    }

    public List<Customer> getAllCustomers() {
        try {
            User userLogged = userService.getLoggedUser();
            return customerRepository.findAllByUser(userLogged);
        } catch (Exception e) {
            throw new BusinessException("No one user logged");
        }
    }

    public void deleteCustomer(Long customerId) {
        Customer customer = findById(customerId);
        customerRepository.delete(customer);
    }
}
