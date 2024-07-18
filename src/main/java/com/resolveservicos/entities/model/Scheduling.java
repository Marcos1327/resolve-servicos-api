package com.resolveservicos.entities.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.resolveservicos.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "scheduling")
public class Scheduling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schedulingId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "service_type_id")
    @JsonBackReference
    private ServiceType serviceType;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate schedulingDate;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime schedulingTime;

    @Enumerated(EnumType.STRING)
    private Status status;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate createdAt;

    public Scheduling(Customer customer, ServiceType serviceType, LocalDate schedulingDate, LocalTime schedulingTime, Status status, LocalDate createdAt) {
        this.customer = customer;
        this.serviceType = serviceType;
        this.schedulingDate = schedulingDate;
        this.schedulingTime = schedulingTime;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Scheduling() {
    }

    public Long getSchedulingId() {
        return schedulingId;
    }

    public void setSchedulingId(Long schedulingId) {
        this.schedulingId = schedulingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public LocalDate getSchedulingDate() {
        return schedulingDate;
    }

    public void setSchedulingDate(LocalDate schedulingDate) {
        this.schedulingDate = schedulingDate;
    }

    public LocalTime getSchedulingTime() {
        return schedulingTime;
    }

    public void setSchedulingTime(LocalTime schedulingTime) {
        this.schedulingTime = schedulingTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
