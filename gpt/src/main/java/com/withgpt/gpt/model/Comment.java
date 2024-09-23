package com.withgpt.gpt.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    private String author;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createdDate;

    // 기본 생성자
    public Comment() {
        this.createdDate = LocalDateTime.now(); // 댓글이 생성된 날짜를 현재 시간으로 설정
    }

    // Getters and Setters

}
