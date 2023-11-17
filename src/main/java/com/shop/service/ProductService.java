package com.shop.service;

import com.shop.dto.ProductFormDto;
import com.shop.dto.ProductImgDto;
import com.shop.dto.ProductSearchDto;
import com.shop.entity.Product;
import com.shop.entity.ProductImg;
import com.shop.repository.ProductImgRepository;
import com.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
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

    @Transactional(readOnly = true)
    public ProductFormDto getProductDtl(Long productId) {
        List<ProductImg> productImgList = productImgRepository.findByProductIdOrderByIdAsc(productId);
        List<ProductImgDto> productImgDtoLIst = new ArrayList<>();
        for (ProductImg productImg : productImgList) {
            ProductImgDto productImgDto = ProductImgDto.of(productImg);
            productImgDtoLIst.add(productImgDto);
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(EntityNotFoundException::new);
        ProductFormDto productFormDto = ProductFormDto.of(product);
        productFormDto.setProductImgDtoList(productImgDtoLIst);
        return productFormDto;
    }

    public Long updateProduct(ProductFormDto productFormDto, List<MultipartFile> productImgFileList) throws Exception {

        Product product = productRepository.findById(productFormDto.getId())
                .orElseThrow(EntityNotFoundException::new);
        product.updateProduct(productFormDto);
        List<Long> productImgIds = productFormDto.getProductImgIds();

        for (int i=0; i<productImgFileList.size(); i++) {
            productImgService.updateProductImg(productImgIds.get(i), productImgFileList.get(i));
        }

        return product.getId();
    }

    @Transactional(readOnly = true)
    public Page<Product> getAdminProductPage(ProductSearchDto productSearchDto, Pageable pageable) {
        return productRepository.getAdminProductPage(productSearchDto, pageable);
    }
}
