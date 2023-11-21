package com.vti.myshopee.model.dto;

import lombok.Data;

@Data
public class OrderCreateDTO {
    private int productId;
    private int accountId;
    private int quantity;


}
