package com.vti.myshopee.repository.specification;

import com.vti.myshopee.model.dto.SearchProductRequest;
import com.vti.myshopee.model.entity.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {


    public static Specification<Product> buildCondition(SearchProductRequest request) {
        return Specification.where(buildConditionName(request))
                .and(buildConditionProductType(request))
                .and(buildConditionShippingUnit(request))
                .and(buildConditionStatus(request))
                .and(buildConditionPrice(request));
    }

    public static Specification<Product> buildConditionName(SearchProductRequest request) {
        if (request.getProductName() != null && !"".equals(request.getProductName())) {
            // Tạo điều kiện tìm kiếm với name
            return (root, query, cri) -> {
// root: Chọn cột, field, để tìm kiếm (giá trị là thuộc tính trong java)
// cri: CriteriaBuilder Khai báo loại so sánh dữ liệu. ( lớn hơn, nhỏ hơn, equal, like,.... )
                return cri.like(root.get("productName"), "%" + request.getProductName() + "%");
            };
        } else {
            return null;
        }
    }

    public static Specification<Product> buildConditionShippingUnit(SearchProductRequest request){
        if (request.getShippingUnit() != null && request.getShippingUnit().size() > 0){
            return (root, query, cri) -> {
                // Tạo điều kiện tìm kiếm với productType. ProductType sẽ là 1 trong các giá trị truyền vào
                return root.get("productType").in(request.getShippingUnit());
            };
        } else {
            return null;
        }
    }

    public static Specification<Product> buildConditionStatus(SearchProductRequest request){
        if (request.getStatus() != null && request.getStatus().size() > 0){
            return (root, query, cri) -> {
                // Tạo điều kiện tìm kiếm với productType. ProductType sẽ là 1 trong các giá trị truyền vào
                return root.get("productType").in(request.getStatus());
            };
        } else {
            return null;
        }
    }

    public static Specification<Product> buildConditionProductType(SearchProductRequest request){
        if (request.getProductType() != null && request.getProductType().size() > 0){
            return (root, query, cri) -> {
                // Tạo điều kiện tìm kiếm với productType. ProductType sẽ là 1 trong các giá trị truyền vào
                return root.get("productType").in(request.getProductType());
            };
        } else {
            return null;
        }
    }

    public static Specification<Product> buildConditionPrice(SearchProductRequest request){
        if (request.getMinPrice() != 0 && request.getMaxPrice() != 0){ // Nếu ko truyền phần tử nào -> lấy tất cả
            return (root, query, cri) -> {
                return cri.between(root.get("price"), request.getMinPrice(), request.getMaxPrice());
            };
        } else {
            return null;
        }
    }






}
