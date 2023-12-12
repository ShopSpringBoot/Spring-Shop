package com.shop.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDto {

    private Long categoryId;

    private String categoryName;

    private Long parentId;

    private List<CategoryDto> subCategory;

    public CategoryDto(Long categoryId, String categoryName, Long parentId) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.parentId = parentId;
    }
}
