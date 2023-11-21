package com.vti.myshopee.model.dto;

import com.vti.myshopee.model.entity.ProductStatus;
import com.vti.myshopee.model.entity.ProductType;
import com.vti.myshopee.model.entity.ShippingUnit;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class ProductCreateDTO {
    private int id;
    @NotBlank(message = "tên sp ko đc để trống")
    private String productName;
    private String image;
    private int price;
    private ProductStatus Status;
    private ShippingUnit shippingUnit ;
    private ProductType productType ;


}
