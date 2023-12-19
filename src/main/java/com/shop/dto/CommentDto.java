package com.shop.dto;

import com.shop.entity.Item;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDto {

    private Long id;

    private String content;

    private String author;

    private LocalDateTime regTime;

    private LocalDateTime updateTime;

}
