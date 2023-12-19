package com.shop.dto;

import com.shop.entity.Comment;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter
public class CommentFormDto {

    private Long id;

    private String content;

    private String author;

    private static ModelMapper modelMapper = new ModelMapper();

    public Comment addComment() {
        return modelMapper.map(this, Comment.class);
    }

    public static CommentFormDto of(Comment comment) {
        return modelMapper.map(comment, CommentFormDto.class);
    }
}
