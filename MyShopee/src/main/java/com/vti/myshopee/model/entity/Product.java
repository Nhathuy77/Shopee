package com.vti.myshopee.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT")
@Getter //Sinh ra các methof getter
@Setter //Sinh ra các methof setter
@AllArgsConstructor //sinh ra hàm khởi tạo không tham số
@NoArgsConstructor //sinh ra hàm khởi tạo có tất cả tham số
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    @Column(name = "IMAGE", unique = true, nullable = false)
    private String image;

    @Column(name = "PRICE", nullable = false)
    private int price;

    @Enumerated(EnumType.STRING)
    private ProductStatus Status;

    @Column(name = "SHIPPING_UNIT", nullable = false)
    @Enumerated(EnumType.STRING)
    private ShippingUnit shippingUnit;

    @Column(name = "PRODUCT_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductType productType;


}
