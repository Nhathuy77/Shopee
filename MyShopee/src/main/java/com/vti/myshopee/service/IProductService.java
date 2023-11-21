package com.vti.myshopee.service;

import com.vti.myshopee.model.dto.ProductCreateDTO;
import com.vti.myshopee.model.dto.ProductUpdateDTO;
import com.vti.myshopee.model.dto.SearchProductRequest;
import com.vti.myshopee.model.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProductService {
    Page<Product> getAll(int page, int size, String sortBy, String sortType);
    Page<Product> search(SearchProductRequest request);
    Product create(ProductCreateDTO dto);
    Product update(ProductUpdateDTO dto);
    Product getById(int id);
    void delete(int id);
}
