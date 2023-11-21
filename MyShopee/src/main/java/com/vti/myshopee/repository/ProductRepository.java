package com.vti.myshopee.repository;

import com.vti.myshopee.model.entity.Product;
import com.vti.myshopee.model.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer >, JpaSpecificationExecutor<Product> {
    List<Product> findByProductNameContainsAndProductTypeIn(String productName, List<ProductType> productTypes);
    boolean existsByProductName(String productName);
}
