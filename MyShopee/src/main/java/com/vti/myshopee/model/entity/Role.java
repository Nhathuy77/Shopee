package com.vti.myshopee.model.entity;

import org.springframework.boot.web.server.GracefulShutdownCallback;
import org.springframework.security.core.GrantedAuthority;
//implements GrantedAuthority: đang coi đối tượng enum Role là 1 quyển tỏng spring security
public enum Role implements GrantedAuthority {
    USER, MANAGER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
