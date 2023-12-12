package com.shop.service;

import com.shop.dto.CategoryDto;
import com.shop.entity.Category;
import com.shop.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    public void 최상위_카테고리_생성() throws Exception {
        //given
        List<Category> categoryEntities = createCategoryEntities();
        given(categoryRepository.findAll())
                .willReturn(categoryEntities);

        //when
        CategoryDto categoryRoot = categoryService.createRootCategory();

        //then
        verify(categoryRepository, atLeastOnce()).findAll();
        // root
        assertThat(categoryRoot.getSubCategory().size(), is(2));
        // sub1
        assertThat(categoryRoot.getSubCategory().get(0).getSubCategory().size(), is(2));
        // sub2
        assertThat(categoryRoot.getSubCategory().get(1).getSubCategory().size(), is(2));
    }

    private List<Category> createCategoryEntities() {
        Category sub1 = new Category("SUB1", 0l);
        Category sub2 = new Category("SUB2", 0l);
        Category sub11 = new Category("SUB1-1", 1l);
        Category sub12 = new Category("SUB1-2", 1l);
        Category sub21 = new Category("SUB2-1", 2l);
        Category sub22 = new Category("SUB2-2", 2l);
        ReflectionTestUtils.setField(sub1, "categoryId", 1l);
        ReflectionTestUtils.setField(sub2, "categoryId", 2l);
        ReflectionTestUtils.setField(sub11, "categoryId", 3l);
        ReflectionTestUtils.setField(sub12, "categoryId", 4l);
        ReflectionTestUtils.setField(sub21, "categoryId", 5l);
        ReflectionTestUtils.setField(sub22, "categoryId", 6l);

        List<Category> categoryEntities = List.of(
                sub1, sub2, sub11, sub12, sub21, sub22
        );
        return categoryEntities;
    }
}