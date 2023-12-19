package com.shop.service;

import com.shop.dto.CommentFormDto;
import com.shop.entity.Comment;
import com.shop.entity.Item;
import com.shop.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Long saveComment(CommentFormDto commentFormDto, Item item) throws Exception {
        Comment comment = commentFormDto.addComment();
        commentRepository.save(comment);

        return comment.getId();
    }

    @Transactional(readOnly = true)
    public List<Comment> getComment(Item item) {
        return commentRepository.findByItem(item);
    }

    public Long updateComment(CommentFormDto commentFormDto, Comment comment) throws Exception {
        commentRepository.save(comment);

        return comment.getId();
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
