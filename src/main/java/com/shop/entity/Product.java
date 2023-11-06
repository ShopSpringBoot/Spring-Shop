package com.shop.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @Column(name="product_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private int price;

    @Lob
    private String description;

    private LocalDateTime dateAdded;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;

    @Builder
    public Product(String productName, int price, String description, LocalDateTime dateAdded, Category category) {
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.dateAdded = LocalDateTime.now();
        this.category = category;
    }

    public void update(String productName, int price, String description, Category category) {
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.category = category;
    }
}
