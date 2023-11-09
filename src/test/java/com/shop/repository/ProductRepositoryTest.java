package com.shop.repository;

import com.shop.constant.ProductSellStatus;
import com.shop.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    @DisplayName("상품 저장 테스트")
    public void createProductTest() {
        Product product = new Product();
        product.setProductNm("테스트 상품");
        product.setPrice(10000);
        product.setItemDetail("테스트 상품 상세 설명");
        product.setItemSellStatus(ProductSellStatus.SELL);
        product.setStockNumber(100);
        product.setRegTime(LocalDateTime.now());
        product.setUpdateTime(LocalDateTime.now());
        Product savedItem = productRepository.save(product);
        System.out.println(savedItem.toString());
    }
}
