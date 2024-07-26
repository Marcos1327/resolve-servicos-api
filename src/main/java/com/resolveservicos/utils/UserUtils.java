package com.resolveservicos.utils;

import com.resolveservicos.entities.dto.UserRecord;
import com.resolveservicos.entities.model.Role;
import com.resolveservicos.enums.RoleName;
import com.resolveservicos.handlers.ResourceNotFoundException;
import com.resolveservicos.repositories.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class UserUtils {

    private final RoleRepository roleRepository;

    public UserUtils(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public static Role convertRoleNameToRole(UserRecord userDTO) {
        Map<RoleName, Role> roleMap = new HashMap<>();
        roleMap.put(RoleName.ROLE_CUSTOMER, new Role(RoleName.ROLE_CUSTOMER));
        roleMap.put(RoleName.ROLE_ADMINISTRATOR, new Role(RoleName.ROLE_ADMINISTRATOR));

        return roleMap.get(userDTO.role());
    }

    public Role convertRoleNameToRole(RoleName roleName) {
        return roleRepository.findByRoleName(roleName).orElseThrow(() -> new ResourceNotFoundException("Role not found"));
    }
}
