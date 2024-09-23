package com.withgpt.gpt.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1000)
    private String content;

    private String author;

    private int viewCount;

    private int likeCount;

    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

    @Enumerated(EnumType.STRING)
    private PostCategory category;

    public Post() {
        this.createdDate = LocalDateTime.now();
    }

    // Getters and Setters
}
