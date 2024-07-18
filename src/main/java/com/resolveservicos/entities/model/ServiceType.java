package com.resolveservicos.entities.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "service_type")
public class ServiceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceTypeId;
    private String serviceTypeName;
    private String description;

    @OneToMany(mappedBy = "serviceType", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Scheduling> scheduling;

    public ServiceType() {
    }

    public ServiceType(String serviceTypeName, String description) {
        this.serviceTypeName = serviceTypeName;
        this.description = description;
    }

    public Long getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(Long serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public void setServiceTypeName(String serviceTypeName) {
        this.serviceTypeName = serviceTypeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
