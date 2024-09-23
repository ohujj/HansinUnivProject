package com.withgpt.gpt.controller;

import com.withgpt.gpt.model.Comment;
import com.withgpt.gpt.model.Post;
import com.withgpt.gpt.model.PostCategory;
import com.withgpt.gpt.service.CommentService;
import com.withgpt.gpt.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/poetry")
public class PoetryController {

    private final PostService postService;
    private final CommentService commentService;

    public PoetryController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping
    public String listPosts(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Post> posts = postService.getPostsByCategory(PostCategory.POETRY, pageable);
        model.addAttribute("posts", posts);
        return "posts/poetry";
    }

    @GetMapping("/{id}")
    public String viewPost(@PathVariable(name = "id") Long id, Model model, HttpSession session) {
        Post post = postService.getPostById(id);
        Set<Long> viewedPosts = (Set<Long>) session.getAttribute("viewedPosts");
        if (viewedPosts == null) {
            viewedPosts = new HashSet<>();
        }
        if (!viewedPosts.contains(id)) {
            postService.incrementViewCount(id);
            viewedPosts.add(id);
            session.setAttribute("viewedPosts", viewedPosts);
        }
        model.addAttribute("post", post);
        model.addAttribute("comments", post.getComments());
        return "posts/poetryDetail";
    }

    @PostMapping("/{id}/like")
    public String likePost(@PathVariable(name = "id") Long id, HttpSession session) {
        Set<Long> likedPosts = (Set<Long>) session.getAttribute("likedPosts");
        if (likedPosts == null) {
            likedPosts = new HashSet<>();
        }
        if (!likedPosts.contains(id)) {
            postService.incrementLike(id);
            likedPosts.add(id);
            session.setAttribute("likedPosts", likedPosts);
        }
        return "redirect:/poetry/" + id;
    }

    @PostMapping("/{id}/comment")
    public String addComment(@PathVariable(name = "id") Long postId, @RequestParam(name = "content") String content) {
        Post post = postService.getPostById(postId);
        Comment comment = new Comment();
        comment.setPost(post);
        comment.setContent(content);
        commentService.saveComment(comment);
        return "redirect:/poetry/" + postId;
    }
}
