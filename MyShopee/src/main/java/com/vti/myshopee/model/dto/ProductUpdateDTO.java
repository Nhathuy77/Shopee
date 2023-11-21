package com.vti.myshopee.model.dto;

import com.vti.myshopee.model.entity.ProductStatus;
import com.vti.myshopee.model.entity.ProductType;
import com.vti.myshopee.model.entity.ShippingUnit;
import lombok.Data;

@Data
public class ProductUpdateDTO {

    private int id;
    private String name;
    private String image;
    private int price;
    private ProductStatus Status;
    private ShippingUnit shippingUnit ;
    private ProductType productType ;
}
