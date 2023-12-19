package com.shop.controller;

import com.shop.dto.CommentFormDto;
import com.shop.entity.Comment;
import com.shop.entity.Item;
import com.shop.repository.CommentRepository;
import com.shop.repository.ItemRepository;
import com.shop.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    private final CommentRepository commentRepository;

    private ItemRepository itemRepository;

    @PostMapping("/{itemId}/new")
    public String createComment(@PathVariable Long itemId, @Valid CommentFormDto commentFormDto, Model model) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(EntityNotFoundException::new);

        try {
            commentService.saveComment(commentFormDto, item);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "리뷰 등록 중 에러가 발생하였습니다.");
        }

        return "item/itemDtl";
    }

    @GetMapping("/{itemId}")
    public List<Comment> viewComment(@PathVariable Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(EntityNotFoundException::new);
        return commentService.getComment(item);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }
}
