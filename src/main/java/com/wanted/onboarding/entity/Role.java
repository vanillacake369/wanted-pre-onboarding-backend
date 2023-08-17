package com.wanted.onboarding.entity;

import java.util.Arrays;
import java.util.Optional;

public enum Role {
    ROLE_MANAGER("ROLE_MANAGER"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    private String role;

    Role(String role_manager) {
        this.role = role_manager;
    }

    public String getRole() {
        return role;
    }

    /* LOOK UP */
    public static Optional<Role> get(String role) {
        return Arrays.stream(Role.values())
                .filter(r -> r.getRole().equals(role))
                .findFirst();
    }
}
