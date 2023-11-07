package com.shop.dto;

import com.shop.entity.Category;
import com.shop.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductDto {
    private String productName;
    private int price;
    private String description;
    private Category category;

    @Builder
    public ProductDto(String productName, int price, String description, Category category) {
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public Product toEntity() {
        return Product.builder()
                .productName(productName)
                .price(price)
                .description(description)
                .category(category)
                .build();
    }
}
