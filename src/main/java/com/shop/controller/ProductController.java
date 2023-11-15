package com.shop.controller;

import com.shop.dto.ProductFormDto;
import com.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "product/new")
    public String productForm(Model model) {
        model.addAttribute("productFormDto", new ProductFormDto());
        return "product/productForm";
    }

    @PostMapping(value = "product/new")
    public String productNew(@Valid ProductFormDto productFormDto, BindingResult bindingResult,
                             Model model, @RequestParam("productImgFile")List<MultipartFile> productImgFileList) {
        if (bindingResult.hasErrors()) {
            return "product/productForm";
        }

        if (productImgFileList.get(0).isEmpty() && productFormDto.getId() == null) {
            model.addAttribute("errorMessage", "대표 이미지를 등록해 주세요.");
            return "product/productForm";
        }

        try {
            productService.saveProduct(productFormDto, productImgFileList);
        } catch (Exception e) {
            model.addAttribute("erroeMessage", "상품 등록 중 에러가 발생하였습니다.");
            return "product/productForm";
        }

        return "redirect:/";
    }

    @GetMapping(value = "product/{productId}")
    public String productDtl(@PathVariable("productId") Long productId, Model model) {

        try {
            ProductFormDto productFormDto = productService.getProductDtl(productId);
            model.addAttribute("productFormDto", productFormDto);
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", "존재하지 않는 상품입니다.");
            model.addAttribute("productFormDto", new ProductFormDto());
            return "product/productForm";
        }

        return "product/productForm";
    }

    @PostMapping(value = "/product/{productId}")
    public String productUpdate(@Valid ProductFormDto productFormDto, BindingResult bindingResult, Model model,
                                @RequestParam("productImgFile") List<MultipartFile> productImgFileList) {

        if (bindingResult.hasErrors()) {
            return "product/productForm";
        }

        if (productImgFileList.get(0).isEmpty() && productFormDto.getId() == null) {
            model.addAttribute("errorMessage", "대표 이미지를 등록해 주세요.");
            return "product/productForm";
        }

        try {
            productService.saveProduct(productFormDto, productImgFileList);
        } catch (Exception e) {
            model.addAttribute("erroeMessage", "상품 수정 중 에러가 발생하였습니다.");
            return "product/productForm";
        }

        return "redirect:/";
    }
}
