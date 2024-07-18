package com.resolveservicos.entities.dto;

public record SchedulingRecord(
        Long serviceTypeId,
        String serviceTypeName,
        String description,
        String schedulingDate,
        String schedulingTime
) {
}
