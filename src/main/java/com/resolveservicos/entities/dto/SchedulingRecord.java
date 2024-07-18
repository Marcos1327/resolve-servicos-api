package com.resolveservicos.entities.dto;

public record SchedulingRecord(
        Long serviceTypeId,
        String schedulingDate,
        String schedulingTime,
        Long customerId
) {
}
