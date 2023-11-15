package com.shop.repository;

import com.shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByProductNm(String itemNm);

    List<Product> findByProductNmOrProductDetail(String itemNm, String itemDetail);

    List<Product> findByPriceLessThan(Integer price);

    List<Product> findByPriceLessThanOrderByPriceDesc(Integer price);
}
