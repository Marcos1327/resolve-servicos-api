package com.resolveservicos.repositories;

import com.resolveservicos.entities.model.Scheduling;
import com.resolveservicos.entities.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {
    Optional<Scheduling> findBySchedulingIdAndUser(Long schedulingId, User userLogged);

    List<Scheduling> findAllByUser(User userLogged);
}
