package com.vti.myshopee.model.dto;

import lombok.Data;

import java.util.Date;
@Data

public class AccountUpdateDTO {
    private int id;
    private String username;
    private String password;
    private Date dateOfBirth;
    private String fullName;
    private String email;
    private String information;
}
