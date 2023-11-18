package com.shop.entity;

import com.shop.constant.ProductSellStatus;
import com.shop.dto.ProductFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="product")
@Getter
@Setter
@ToString
public class Product extends BaseEntity {

    @Id
    @Column(name="product_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;                        //상품코드

    @Column(nullable = false, length = 50)
    private String productNm;                  //상품명

    @Column(name="price", nullable = false)
    private int price;                      //가격

    @Column(nullable = false)
    private int stockNumber;                //재고수량

    @Lob
    @Column(nullable = false)
    private String productDetail;              //상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ProductSellStatus productSellStatus;  //상품 판매 상태

    private LocalDateTime regTime;          //등록 시간

    private LocalDateTime updateTime;       //수정 시간

    public void updateProduct(ProductFormDto productFormDto) {
        this.productNm = productFormDto.getProductNm();
        this.price = productFormDto.getPrice();
        this.stockNumber = productFormDto.getStockNumber();
        this.productDetail = productFormDto.getProductDetail();
        this.productSellStatus = productFormDto.getProductSellStatus();
    }
}
