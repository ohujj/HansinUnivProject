package com.withgpt.gpt.config;

import com.withgpt.gpt.model.Post;
import com.withgpt.gpt.model.PostCategory;
import com.withgpt.gpt.repository.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DBInit {

    @Bean
    public CommandLineRunner initDatabase(PostRepository postRepository) {
        return args -> {
            // 샘플 소설 게시글 추가
            Post post1 = new Post();
            post1.setTitle("첫 번째 소설");
            post1.setContent("이것은 첫 번째 소설의 내용입니다.");
            post1.setAuthor("작가 1");
            post1.setViewCount(0);
            post1.setLikeCount(0);
            post1.setCategory(PostCategory.NOVEL);
            post1.setCreatedDate(LocalDateTime.now());

            Post post2 = new Post();
            post2.setTitle("두 번째 소설");
            post2.setContent("이것은 두 번째 소설의 내용입니다.");
            post2.setAuthor("작가 2");
            post2.setViewCount(0);
            post2.setLikeCount(0);
            post2.setCategory(PostCategory.NOVEL);
            post2.setCreatedDate(LocalDateTime.now());

            Post post3 = new Post();
            post3.setTitle("에세이1");
            post3.setContent("에세이테스트");
            post3.setAuthor("작가 3");
            post3.setViewCount(0);
            post3.setLikeCount(0);
            post3.setCategory(PostCategory.ESSAY);
            post3.setCreatedDate(LocalDateTime.now());

            Post post4 = new Post();
            post4.setTitle("시테스트");
            post4.setContent("시");
            post4.setAuthor("작가 4");
            post4.setViewCount(0);
            post4.setLikeCount(0);
            post4.setCategory(PostCategory.POETRY);
            post4.setCreatedDate(LocalDateTime.now());

            Post post5 = new Post();
            post5.setTitle("일기테스트");
            post5.setContent("일기");
            post5.setAuthor("작가 5");
            post5.setViewCount(0);
            post5.setLikeCount(0);
            post5.setCategory(PostCategory.DIARY);
            post5.setCreatedDate(LocalDateTime.now());

            // 데이터베이스에 저장
            postRepository.save(post1);
            postRepository.save(post2);
            postRepository.save(post3);
            postRepository.save(post4);
            postRepository.save(post5);



            System.out.println("소설 게시글이 성공적으로 추가되었습니다.");
        };
    }
}
