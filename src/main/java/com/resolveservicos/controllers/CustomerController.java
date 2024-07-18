package com.resolveservicos.controllers;

import com.resolveservicos.entities.dto.CustomerRecord;
import com.resolveservicos.entities.model.Customer;
import com.resolveservicos.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerRecord customerRecord) {
        return ResponseEntity.ok(customerService.createCustomer(customerRecord));
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<Customer> getCustomerByName(@PathVariable String name) {
        return ResponseEntity.ok(customerService.getCustomerByName(name));
    }

    @PatchMapping("/update/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long customerId, @RequestBody CustomerRecord customerRecord) {
        return ResponseEntity.ok(customerService.updateCustomer(customerId, customerRecord));
    }

    @GetMapping("/findById/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long customerId) {
        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }


    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.noContent().build();
    }
}
