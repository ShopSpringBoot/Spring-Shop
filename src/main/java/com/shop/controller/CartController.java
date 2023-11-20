package com.shop.controller;


import com.shop.dto.CartDetailDto;
import com.shop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    // 장바구니 상품의 수량만 업데이트
    @PatchMapping(value = "/cartItem/{cartItemId}")
    public @ResponseBody ResponseEntity updateCartItem (@PathVarible("cartItemId") Long cartItemId, int count, Principal principal) {

        // 장바구니에 담겨있는 상품의 개수가 0이하일 경우 에러 메시지 반환
        if (count <= 0) {
            return new ResponseEntity<String> ("최소 1개 이상 담아주세요", HttpStatus.BAD_REQUEST);
        // 수정 권한 체크
        } else if (!CartService.validateCartItem (cartItemId, principal.getName())) {
            return new ResponseEntity<String> ("수정 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        // 장바구니 상품의 개수 업데이트
        cartService.updateCartItemCount(cartItemId, count);
        return new ResponseEntity<Long> (cartItemId, HttpStatus.OK);
    }

}
