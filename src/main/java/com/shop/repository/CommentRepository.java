package com.shop.repository;

import com.shop.entity.Comment;
import com.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByItem(Item item);
}
