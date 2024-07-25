package com.resolveservicos.entities.dto;

import com.resolveservicos.enums.RoleName;

public record UserRecord(String name, String email, String password, RoleName role) {
}
