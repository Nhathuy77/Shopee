package com.vti.myshopee.model.dto;


import com.vti.myshopee.model.entity.Role;
import lombok.Data;

@Data
public class AccountLoginResponse {
    private long id;
    private String username;
    private String fullName;
    private String avatar;
    private String address;
    private Role role;

    private String token;
}
