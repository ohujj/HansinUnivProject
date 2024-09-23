package com.withgpt.gpt.repository;

import com.withgpt.gpt.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
