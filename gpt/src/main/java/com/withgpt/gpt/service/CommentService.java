package com.withgpt.gpt.service;

import com.withgpt.gpt.model.Comment;
import com.withgpt.gpt.repository.CommentRepository;
import org.springframework.stereotype.Service;


@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }
}
