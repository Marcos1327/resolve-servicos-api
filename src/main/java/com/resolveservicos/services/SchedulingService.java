package com.resolveservicos.services;

import com.resolveservicos.enums.Status;
import com.resolveservicos.entities.dto.SchedulingRecord;
import com.resolveservicos.entities.model.Scheduling;
import com.resolveservicos.handlers.BusinessException;
import com.resolveservicos.handlers.ResourceNotFoundException;
import com.resolveservicos.repositories.SchedulingRepository;
import com.resolveservicos.utils.Util;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

        if (!util.isTimeBeforeNow(schedulingRecord.schedulingDate(), schedulingRecord.schedulingTime())) {
            throw new BusinessException("Scheduling time cannot be a past time!");
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

    public Scheduling update(Long schedulingId, SchedulingRecord schedulingRecord) {
        Scheduling scheduling = schedulingRepository.findById(schedulingId).orElseThrow(() -> new ResourceNotFoundException("Scheduling not found!"));

        if(scheduling.getStatus() == Status.CANCELADO) {
            throw new BusinessException("The scheduling has already been cancelled. Create another scheduling!");
        }

        if (schedulingRecord.customerId() == null || schedulingRecord.serviceTypeId() == null || schedulingRecord.schedulingDate() == null || schedulingRecord.schedulingTime() == null) {
            throw new BusinessException("customerId, serviceTypeId, schedulingDate and schedulingTime are required fields!");
        }

        if (util.isDateBeforeNow(util.convertStringToLocalDate(schedulingRecord.schedulingDate()))) {
            throw new BusinessException("Scheduling date cannot be a past date!");
        }

        if (!util.isTimeBeforeNow(schedulingRecord.schedulingDate(), schedulingRecord.schedulingTime())) {
            throw new BusinessException("Scheduling time cannot be a past time!");
        }

        if (schedulingRecord.customerId() != null){
            scheduling.setCustomer(customerService.getCustomerById(schedulingRecord.customerId()));
        }
        if (schedulingRecord.serviceTypeId() != null){
            scheduling.setServiceType(serviceTypeService.getServiceTypeById(schedulingRecord.serviceTypeId()));
        }

        if (schedulingRecord.schedulingDate() != null){
            scheduling.setSchedulingDate(util.convertStringToLocalDate(schedulingRecord.schedulingDate()));
        }

        if (schedulingRecord.schedulingTime() != null){
            scheduling.setSchedulingTime(util.convertStringToLocalTime(schedulingRecord.schedulingTime()));
        }

        scheduling.setCreatedAt(LocalDate.now());

        schedulingRepository.save(scheduling);

        return scheduling;
    }

    public void cancel(Long schedulingId) {
        Scheduling scheduling = schedulingRepository.findById(schedulingId).orElseThrow(() -> new ResourceNotFoundException("Scheduling not found!"));
        scheduling.setStatus(Status.CANCELADO);
        schedulingRepository.save(scheduling);
    }

    public Scheduling conclude(Long schedulingId) {
        Scheduling scheduling = schedulingRepository.findById(schedulingId).orElseThrow(() -> new ResourceNotFoundException("Scheduling not found!"));

        if(scheduling.getStatus() != Status.CANCELADO && scheduling.getStatus() != Status.CONCLUIDO) {
            scheduling.setStatus(Status.CONCLUIDO);
            schedulingRepository.save(scheduling);
        } else {
            throw new BusinessException("The scheduling has already been finished or canceled!");
        }
        return scheduling;
    }

    public List<Scheduling> getAllSchedulings() {
        return schedulingRepository.findAll();
    }
}
