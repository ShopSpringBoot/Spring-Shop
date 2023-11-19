package com.shop.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Category {

    @Id
    @Column(name="category_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String categoryName;

    public void update(String categoryName) {
        this.categoryName = categoryName;
    }
}