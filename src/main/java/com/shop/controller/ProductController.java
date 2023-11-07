package com.shop.controller;

import com.shop.dto.ProductDto;
import com.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProductController {
    private final ProductService productService;

    @PostMapping("/product")
    public Long save(@RequestBody ProductDto productDto) {
        return productService.save(productDto);
    }
}
