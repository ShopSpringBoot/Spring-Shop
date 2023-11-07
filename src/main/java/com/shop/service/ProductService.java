package com.shop.service;

import com.shop.dto.ProductDto;
import com.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public long save(ProductDto productDto) {
        return productRepository.save(productDto.toEntity()).getId();
    }
}
