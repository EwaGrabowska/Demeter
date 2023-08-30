package com.demeter.recipeservice.repository;

import com.demeter.recipeservice.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

interface CommentRepository extends JpaRepository<Comment, Long> {
}
