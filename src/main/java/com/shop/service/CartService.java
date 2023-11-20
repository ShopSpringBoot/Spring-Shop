package com.shop.service;

import com.shop.dto.CartDetailDto;
import com.shop.entity.Cart;
import com.shop.entity.CartItem;
import com.shop.entity.Member;
import com.shop.repository.CartItemRepository;
import com.shop.repository.CartRepository;
import com.shop.repository.ItemRepository;
import com.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Transactional(readOnly = true)
    public List<CartDetailDto> getCartList(String email) {
        List<CartDetailDto> cartDetailDtoList = new ArrayList<>();

        Member member = memberRepository.findByEmail(email);
        // 현재 로그인한 회원의 장바구니 엔터티 조회
        Cart cart = cartRepository.findByMemberId(member.getId());
        // 장바구니에 상품을 한 번도 안 담았을 경우 빈 리스트 반환(장바구니 엔터티 없음)
        if(cart == null) {
            return cartDetailDtoList;
        }

        // 장바구니에 담겨있는 상품 정보 조회
        cartDetailDtoList = cartItemRepository.findCartDetailDtoList(cart.getId());

        return cartDetailDtoList;
    }
}
