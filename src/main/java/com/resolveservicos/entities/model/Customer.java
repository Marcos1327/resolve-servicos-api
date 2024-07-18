package com.resolveservicos.entities.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    private String name;
    private String contactNumber;
    private String address;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "scheduling_id")
    @JsonBackReference
    private Scheduling scheduling;

    public Customer() {
    }

    public Customer(String name, String contactNumber, String address, LocalDate createdAt, Scheduling scheduling) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.address = address;
        this.createdAt = createdAt;
        this.scheduling = scheduling;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Scheduling getScheduling() {
        return scheduling;
    }

    public void setScheduling(Scheduling scheduling) {
        this.scheduling = scheduling;
    }
}
