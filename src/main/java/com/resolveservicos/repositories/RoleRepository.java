package com.resolveservicos.repositories;

import com.resolveservicos.entities.model.Role;
import com.resolveservicos.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(RoleName roleName);
}
