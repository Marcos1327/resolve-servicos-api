package com.resolveservicos.controllers;

import com.resolveservicos.entities.dto.ServiceTypeRecord;
import com.resolveservicos.entities.model.ServiceType;
import com.resolveservicos.services.ServiceTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/service-types")
public class ServiceTypeController {

    private final ServiceTypeService serviceTypeService;

    public ServiceTypeController(ServiceTypeService serviceTypeService) {
        this.serviceTypeService = serviceTypeService;
    }

    @PostMapping("/create")
    public ResponseEntity<ServiceType> createServiceType(@RequestBody ServiceTypeRecord serviceTypeDTO) {
        return ResponseEntity.ok(serviceTypeService.createServiceType(serviceTypeDTO));
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<ServiceType>> findAllServiceTypes() {
        return ResponseEntity.ok(serviceTypeService.getAllServiceTypes());
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<ServiceType> findServiceTypeById(@PathVariable Long id) {
        return ResponseEntity.ok(serviceTypeService.getServiceTypeById(id));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ServiceType> updateServiceType(@PathVariable Long id, @RequestBody ServiceTypeRecord serviceTypeDTO) {
        return ResponseEntity.ok(serviceTypeService.updateServiceType(id, serviceTypeDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteServiceType(@PathVariable Long id) {
        serviceTypeService.deleteServiceType(id);
        return ResponseEntity.noContent().build();
    }
}
