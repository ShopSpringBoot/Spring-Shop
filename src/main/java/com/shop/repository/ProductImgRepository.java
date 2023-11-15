package com.shop.repository;

import com.shop.entity.ProductImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImgRepository extends JpaRepository<ProductImg, Long> {

    List<ProductImg> findByProductIdOrderByIdAsc(Long productId);

    ProductImg findByProductIdAndIsThumbnail(Long productId, String isThumbnail);
}
