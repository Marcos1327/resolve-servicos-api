package com.resolveservicos.repositories;

import com.resolveservicos.entities.model.Scheduling;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {
}
