package com.shop.repository;

import com.shop.dto.ProductSearchDto;
import com.shop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {

    Page<Product> getAdminProductPage(ProductSearchDto productSearchDto, Pageable pageable);
}
