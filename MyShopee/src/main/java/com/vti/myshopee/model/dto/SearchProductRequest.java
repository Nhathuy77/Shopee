package com.vti.myshopee.model.dto;

import com.vti.myshopee.model.entity.ProductStatus;
import com.vti.myshopee.model.entity.ProductType;
import com.vti.myshopee.model.entity.ShippingUnit;
import lombok.Data;

import java.util.List;

@Data
public class SearchProductRequest {
    private String productName;
    private List<ProductStatus> Status;
    private List<ShippingUnit> shippingUnit ;
    private List<ProductType> productType ;
    private Long minPrice;
    private Long maxPrice;

    //Các thuộc tính để phân trang và sắp xếp
    private int page;
    private int size;
    private String sortBy; //thuộc tính entity trong java (vd price)
    private String sortType; // cps 2 giá trị là ASC(tăng dần), DESC(giảm dân)

}
