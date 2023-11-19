package com.shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="product_img")
@Getter @Setter
public class ProductImg extends BaseEntity {

    @Id
    @Column(name="product_img_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String imgName;

    private String ogImgName;

    private String imgUrl;

    private String isThumbnail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    public void updateProductImg(String ogImgName, String imgName, String imgUrl) {
        this.ogImgName = ogImgName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }
}
