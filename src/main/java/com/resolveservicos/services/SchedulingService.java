package com.resolveservicos.services;

import com.resolveservicos.Enums.Status;
import com.resolveservicos.entities.dto.SchedulingRecord;
import com.resolveservicos.entities.model.Scheduling;
import com.resolveservicos.handlers.BusinessException;
import com.resolveservicos.handlers.ResourceNotFoundException;
import com.resolveservicos.repositories.SchedulingRepository;
import com.resolveservicos.utils.Util;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SchedulingService {

    private final SchedulingRepository schedulingRepository;
    private final CustomerService customerService;

    private final ServiceTypeService serviceTypeService;

    private final Util util;

    public SchedulingService(SchedulingRepository schedulingRepository, CustomerService customerService, ServiceTypeService serviceTypeService, Util util) {
        this.schedulingRepository = schedulingRepository;
        this.customerService = customerService;
        this.serviceTypeService = serviceTypeService;
        this.util = util;
    }

    public Scheduling create(SchedulingRecord schedulingRecord) {
        Scheduling scheduling = new Scheduling();

        if (schedulingRecord.customerId() == null || schedulingRecord.serviceTypeId() == null || schedulingRecord.schedulingDate() == null || schedulingRecord.schedulingTime() == null) {
            throw new BusinessException("customerId, serviceTypeId, schedulingDate and schedulingTime are required fields!");
        }

        if (util.isDateBeforeNow(util.convertStringToLocalDate(schedulingRecord.schedulingDate()))) {
            throw new BusinessException("Scheduling date cannot be a past date!");
        }

        scheduling.setCustomer(customerService.getCustomerById(schedulingRecord.customerId()));
        scheduling.setServiceType(serviceTypeService.getServiceTypeById(schedulingRecord.serviceTypeId()));
        scheduling.setSchedulingDate(util.convertStringToLocalDate(schedulingRecord.schedulingDate()));
        scheduling.setSchedulingTime(util.convertStringToLocalTime(schedulingRecord.schedulingTime()));
        scheduling.setStatus(Status.AGENDADO);
        scheduling.setCreatedAt(LocalDate.now());

        schedulingRepository.save(scheduling);

        return scheduling;
    }
}
