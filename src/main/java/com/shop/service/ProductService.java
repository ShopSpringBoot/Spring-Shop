package com.shop.service;

import com.shop.dto.ProductFormDto;
import com.shop.entity.Product;
import com.shop.entity.ProductImg;
import com.shop.repository.ProductImgRepository;
import com.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImgService productImgService;
    private final ProductImgRepository productImgRepository;

    public Long saveProduct(ProductFormDto productFormDto, List<MultipartFile> productImgFileList) throws Exception {

        Product product = productFormDto.createProduct();
        productRepository.save(product);

        for (int i=0; i<productImgFileList.size(); i++) {
            ProductImg productImg = new ProductImg();
            productImg.setProduct(product);

            if (i == 0) {
                productImg.setIsThumbnail("Y");
            } else {
                productImg.setIsThumbnail("N");
            }

            productImgService.saveProductImg(productImg, productImgFileList.get(i));
        }

        return product.getId();
    }
}
