package com.shop.repository;

import com.shop.dto.CartDetailDto;
import com.shop.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

      CartItem findByCartIdAndItemId(Long cartId, Long itemId);
  
    // CartDetailDto 생성자를 이용하여 DTO 반환
    // 생성자의 파라미터 순서는 DTO클래스에 명시한 순서
    @Query("select new com.shop.dto.CartDetailDto(ci.id, i.itemNm, i.price, ci.count, im.imgUrl) " +
            "from CartItem ci, ItemImg im " +
            "join ci.item i " +
            "where ci.cart.id = :cartId " +
            // 장바구니에 담겨있는 상품의 대표 이미지만 가져옴
            "and im.item.id = ci.item.id " +
            // 장바구니에 담겨있는 상품의 대표 이미지만 가져옴
            "and im.repimgYn = 'Y' " +
            "order by ci.regTime desc"
    )
    List<CartDetailDto> findCartDetailDtoList(Long cartId);
  }