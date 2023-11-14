package com.shop.dto;

import com.shop.constant.ProductSellStatus;
import com.shop.entity.Product;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductFormDto {

    private Long id;

    @NotBlank(message = "상품명을 입력해 주세요.")
    private String productName;

    @NotNull(message = "가격을 입력해 주세요.")
    private Integer price;

    @NotBlank(message = "설명을 작성해 주세요.")
    private String productDetail;

    @NotNull(message = "재고를 입력해 주세요.")
    private Integer stockNum;

    private ProductSellStatus productSellStatus;

    private List<ProductImgDto> productImgDtoList = new ArrayList<>();

    private List<Long> productImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public Product createProduct() {
        return modelMapper.map(this, Product.class);
    }

    public static ProductFormDto of(Product product) {
        return modelMapper.map(product, ProductFormDto.class);
    }
}
