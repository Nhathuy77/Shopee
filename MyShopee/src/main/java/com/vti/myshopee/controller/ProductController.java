package com.vti.myshopee.controller;


import com.vti.myshopee.model.dto.ProductCreateDTO;
import com.vti.myshopee.model.dto.ProductUpdateDTO;
import com.vti.myshopee.model.dto.SearchProductRequest;
import com.vti.myshopee.model.entity.Product;
import com.vti.myshopee.service.iml.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@CrossOrigin("*")
@Validated
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/get-all")
    public Page<Product> getAll(int page, int size, String sortBy, String sortType) {
        return productService.getAll(page, size, sortBy, sortType);
    }

    @PostMapping("/create")
    public Product create(@RequestBody ProductCreateDTO dto) {
        return productService.create(dto);

    }

    @PutMapping("/update")
    public Product update(@RequestBody ProductUpdateDTO dto) {
        return productService.update(dto);
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable int id) {
        return productService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        productService.delete(id);
    }

    @GetMapping("/{id}")
    public Page<Product> search(@RequestBody SearchProductRequest request) {
        return productService.search(request);
    }

}