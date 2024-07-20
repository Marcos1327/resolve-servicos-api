package com.resolveservicos.repositories;

import com.resolveservicos.entities.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
