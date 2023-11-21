package com.vti.myshopee.service.iml;

import com.vti.myshopee.config.exception.CustomException;
import com.vti.myshopee.config.exception.ErrorResponseEnum;
import com.vti.myshopee.model.dto.ProductCreateDTO;
import com.vti.myshopee.model.dto.ProductUpdateDTO;
import com.vti.myshopee.model.dto.SearchProductRequest;
import com.vti.myshopee.model.entity.Product;
import com.vti.myshopee.repository.ProductRepository;
import com.vti.myshopee.repository.specification.ProductSpecification;
import com.vti.myshopee.service.IProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> getAll(int page, int size, String sortBy, String sortType) {
        //
        Sort sort = null;
       if (!"ASC".equalsIgnoreCase(sortType)) {
           sort = Sort.by(sortBy).ascending();
       }  else{
               sort = Sort.by(sortBy).descending();
           }
//        Pageable pageable = Pageable.ofSize(size).withPage(page);
       //tạp ra đôie tượng Pageable có sắp xếp
        Pageable pageable = PageRequest.of(page-1, size, sort);
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> search(SearchProductRequest request) {
        // tạo ra điều kiện từ class Specification
        Specification<Product> condition = ProductSpecification.buildCondition(request);
        //Tạo phân trang, sắp xếp
        Sort sort = null;
        if (!"ASC".equalsIgnoreCase(request.getSortType())) {
            sort = Sort.by(request.getSortBy()).ascending();
        }  else{
            sort = Sort.by(request.getSortBy()).descending();
        }
//        Pageable pageable = Pageable.ofSize(size).withPage(page);
        //tạp ra đôie tượng Pageable có sắp xếp
        Pageable pageable = PageRequest.of(request.getPage()-1, request.getSize(),sort);
        return productRepository.findAll(condition, pageable);
//            return productRepository.findByProductNameContainsAndProductTypeIn(request.getProductName(),request.getProductTypes());
        }


    @Override
    public Product create(ProductCreateDTO dto) {
        // ----Validate(kiểm tra) dữ liệu đầu vào-----------
        //   (Validate những đk mà java có thể check đc trước rồi
        //   mới Validate những đk trong database ---> Tối ưu thời gian)
        //kiểm tra sự tồn tại của productName
        boolean checkProductName = productRepository.existsByProductName(dto.getProductName());
        if (checkProductName){
            throw new CustomException(ErrorResponseEnum.NOT_FOUND_PRODUCT);
        }

        Product product = new Product();
        BeanUtils.copyProperties(dto, product);
        return productRepository.save(product);
    }

    @Override
    public Product update(ProductUpdateDTO dto) {
        Optional<Product> productOptional = productRepository.findById(dto.getId());
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            BeanUtils.copyProperties(dto, product);
            return productRepository.save(product);
        }
        return null;
    }

    @Override
    public Product getById(int id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            return productOptional.get();
        }

        return null;
    }

    @Override
    public void delete(int id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            productRepository.deleteById(id);
        }


    }
}
