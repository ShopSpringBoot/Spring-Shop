package com.shop.service;

import com.shop.entity.ProductImg;
import com.shop.repository.ProductImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductImgService {

    @Value("${productImgLocation}")
    private String productImgLocation;

    private final ProductImgRepository productImgRepository;

    private final FileService fileService;

    public void saveProductImg(ProductImg productImg, MultipartFile productImgFile) throws Exception {
        String ogImgName = productImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        if (!StringUtils.isEmpty(ogImgName)) {
            imgName = fileService.uploadFile(productImgLocation, ogImgName, productImgFile.getBytes());
            imgUrl = "/images/product/" + imgName;
        }

        productImg.updateProductImg(ogImgName, imgName, imgUrl);
        productImgRepository.save(productImg);
    }

    public void updateProductImg(Long productImgId, MultipartFile productImgFile) throws Exception {
        if (!productImgFile.isEmpty()) {
            ProductImg savedProductImg = productImgRepository.findById(productImgId)
                    .orElseThrow(EntityNotFoundException::new);

            if (!StringUtils.isEmpty(savedProductImg.getImgName())) {
                fileService.deleteFIle(productImgLocation + "/" + savedProductImg.getImgName());
            }

            String ogImgName = productImgFile.getOriginalFilename();
            String imgName = fileService.uploadFile(productImgLocation,ogImgName, productImgFile.getBytes());
            String imgUrl = "/images/product/" + imgName;
            savedProductImg.updateProductImg(ogImgName, imgName, imgUrl);
        }
    }
}
