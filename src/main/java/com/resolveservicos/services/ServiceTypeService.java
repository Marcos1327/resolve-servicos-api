package com.resolveservicos.services;

import com.resolveservicos.entities.dto.ServiceTypeRecord;
import com.resolveservicos.entities.model.ServiceType;
import com.resolveservicos.handlers.ResourceNotFoundException;
import com.resolveservicos.repositories.ServiceTypeRepository;
import com.resolveservicos.utils.Util;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTypeService {

    private final ServiceTypeRepository serviceTypeRepository;
    private final Util util;

    public ServiceTypeService(ServiceTypeRepository serviceTypeRepository, Util util) {
        this.serviceTypeRepository = serviceTypeRepository;
        this.util = util;
    }

    public ServiceType createServiceType(ServiceTypeRecord serviceTypeRecord) {
        ServiceType serviceType = new ServiceType();

        if ( util.isNullOrEmpty(serviceTypeRecord.serviceTypeName())) {
            throw new ResourceNotFoundException("Service Type is required!");
        }

        serviceType.setServiceTypeName(serviceTypeRecord.serviceTypeName());
        serviceType.setDescription(serviceTypeRecord.description());

        serviceTypeRepository.save(serviceType);
        return serviceType;
    }

    public List<ServiceType> getAllServiceTypes() {
        return serviceTypeRepository.findAll();
    }

    public ServiceType getServiceTypeById(Long serviceTypeId) {
        return findById(serviceTypeId);
    }

    public void deleteServiceType(Long serviceTypeId) {
        ServiceType serviceType = findById(serviceTypeId);
        serviceTypeRepository.delete(serviceType);
    }
    public ServiceType updateServiceType(Long serviceTypeId, ServiceTypeRecord serviceTypeRecord) {
        ServiceType serviceType = findById(serviceTypeId);

        if (serviceTypeRecord.serviceTypeName() != null) {
            serviceType.setServiceTypeName(serviceTypeRecord.serviceTypeName());
        }
        if (serviceTypeRecord.description() != null) {
            serviceType.setDescription(serviceTypeRecord.description());
        }

        serviceTypeRepository.save(serviceType);
        return serviceType;
    }
    private ServiceType findById(Long serviceTypeId) {
        return serviceTypeRepository.findById(serviceTypeId).orElseThrow(() -> new ResourceNotFoundException("Service Type not found with id: " + serviceTypeId));
    }
}
