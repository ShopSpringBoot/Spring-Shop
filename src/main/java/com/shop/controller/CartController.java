package com.shop.controller;


import com.shop.dto.CartDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {

    @GetMapping(value = "/cart")
    public String orderHist(Principal principal, Model model) {
        // 현재 로그인한 사용자의 이메일 정보를 이용한 장바구니에 담겨있는 상품 정보 조회
        List<CartDetailDto> cartDetailDtoList = cartService.getCartList(principal.getName());
        // 조회한 장바구니 상품 정보를 뷰로 전달
        model.addAttribute("cartItems", cartDetailDtoList);
        return "cart/cartList";
    }

}
