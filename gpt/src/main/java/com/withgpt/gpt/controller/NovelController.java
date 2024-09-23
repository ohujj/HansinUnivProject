package com.withgpt.gpt.controller;

import com.withgpt.gpt.model.Comment;
import com.withgpt.gpt.model.Post;
import com.withgpt.gpt.model.PostCategory;
import com.withgpt.gpt.service.CommentService;
import com.withgpt.gpt.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/novel")
public class NovelController {

    private final PostService postService;
    private final CommentService commentService;

    public NovelController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping
    public String listPosts(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Post> posts = postService.getPostsByCategory(PostCategory.NOVEL, pageable);
        model.addAttribute("posts", posts);
        return "posts/novel";
    }

    @GetMapping("/{id}")
    public String viewPost(@PathVariable(name = "id") Long id, Model model, HttpSession session) {
        Post post = postService.getPostById(id);

        // 세션에서 본 게시글을 기록하는 set 가져오기
        Set<Long> viewedPosts = (Set<Long>) session.getAttribute("viewedPosts");
        if (viewedPosts == null) {
            viewedPosts = new HashSet<>();
        }

        // 게시글 조회수가 세션에서 한 번만 증가되도록 설정
        if (!viewedPosts.contains(id)) {
            postService.incrementViewCount(id);
            viewedPosts.add(id);
            session.setAttribute("viewedPosts", viewedPosts);
        }

        model.addAttribute("post", post);
        model.addAttribute("comments", post.getComments());
        return "posts/novelDetail";
    }

    @PostMapping("/{id}/like")
    public String likePost(@PathVariable(name = "id") Long id, HttpSession session) {
        // 세션에서 좋아요를 기록하는 set 가져오기
        Set<Long> likedPosts = (Set<Long>) session.getAttribute("likedPosts");
        if (likedPosts == null) {
            likedPosts = new HashSet<>();
        }

        // 좋아요가 세션에서 한 번만 증가되도록 설정
        if (!likedPosts.contains(id)) {
            postService.incrementLike(id);
            likedPosts.add(id);
            session.setAttribute("likedPosts", likedPosts);
        }

        return "redirect:/novel/" + id;
    }

    // 댓글 작성 처리
    @PostMapping("/{id}/comment")
    public String addComment(@PathVariable(name = "id") Long postId, @RequestParam(name = "content") String content) {
        // 로그인한 사용자의 이름을 가져와서 댓글의 author로 설정
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String author;

        if (principal instanceof UserDetails) {
            author = ((UserDetails) principal).getUsername();
        } else {
            author = principal.toString();
        }

        // 댓글 객체 생성 및 저장
        Post post = postService.getPostById(postId);
        Comment comment = new Comment();
        comment.setPost(post);
        comment.setContent(content);
        comment.setAuthor(author);
        commentService.saveComment(comment);

        return "redirect:/novel/" + postId;
    }
}
