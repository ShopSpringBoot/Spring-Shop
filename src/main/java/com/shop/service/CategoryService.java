package com.shop.service;

import com.shop.dto.CategoryDto;
import com.shop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryDto createRootCategory() {
        Map<Long, List<CategoryDto>> groupingByParent = categoryRepository.findAll()
                .stream()
                .map(ce -> new CategoryDto(ce.getCategoryId(), ce.getCategoryName(), ce.getParentId()))
                .collect(groupingBy(cd -> cd.getParentId()));

        CategoryDto rootCategoryDto = new CategoryDto(0l, "ROOT", null);
        addSubCategories(rootCategoryDto, groupingByParent);

        return rootCategoryDto;
    }

    private void addSubCategories(CategoryDto parent, Map<Long, List<CategoryDto>> groupingByParentId) {
        List<CategoryDto> subCategories = groupingByParentId.get(parent.getCategoryId());

        if (subCategories == null)
            return;

        parent.setSubCategories(subCategories);

        subCategories.stream()
                .forEach(s -> {
                    addSubCategories(s, groupingByParentId);
                });
    }
}
