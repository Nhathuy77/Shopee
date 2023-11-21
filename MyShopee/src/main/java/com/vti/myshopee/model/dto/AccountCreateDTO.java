package com.vti.myshopee.model.dto;

import com.vti.myshopee.model.entity.Role;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class AccountCreateDTO {
    @NotBlank(message = "Tên người dùng không được để trống")


    private int id;
    private String username;
    private String password;
    private Date dateOfBirth;
    private String fullName;
    private String email;
    private String information;




}
