package com.shop.controller;

import com.shop.dto.ProductFormDto;
import com.shop.dto.ProductSearchDto;
import com.shop.entity.Product;
import com.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/admin/product/new")
    public String productForm(Model model) {
        model.addAttribute("productFormDto", new ProductFormDto());
        return "product/productForm";
    }

    @PostMapping(value = "/admin/product/new")
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

    @GetMapping(value = "/admin/product/{productId}")
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

    @PostMapping(value = "/admin/product/{productId}")
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

    @GetMapping(value = {"/admin/products", "/admin/products/{page}"})
    public String productMange(ProductSearchDto productSearchDto,
                               @PathVariable("page")Optional<Integer> page, Model model) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 3);

        Page<Product> products = productService.getAdminProductPage(productSearchDto, pageable);

        model.addAttribute("products", products);
        model.addAttribute("productSearchDto", productSearchDto);
        model.addAttribute("maxPage", 5);

        return "product/productMng";
    }

    @GetMapping(value = "/product/{productId}")
    public String productDtl(Model model, @PathVariable("productId") Long productId){
        ProductFormDto productFormDto = productService.getProductDtl(productId);
        model.addAttribute("product", productFormDto);
        return "product/productDtl";
    }
}
