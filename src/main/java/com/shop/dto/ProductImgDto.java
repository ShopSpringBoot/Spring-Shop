package com.shop.dto;

import com.shop.entity.ProductImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class ProductImgDto {

    private Long id;

    private String imgName;

    private String ogImgName;

    private String imgUrl;

    private String isThumbnail;

    private static ModelMapper modelMapper = new ModelMapper();

    public static ProductImgDto of(ProductImg productImg) {
        return modelMapper.map(productImg, ProductImgDto.class);
    }
}
