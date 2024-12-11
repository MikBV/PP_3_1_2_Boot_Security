package ru.kata.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;

public enum EnumRole implements GrantedAuthority {
    ROLE_ADMIN,ROLE_USER,ROLE_ANONYMOUS;

    @Override
    public String getAuthority() {
        return "";
    }
}
