package com.withgpt.gpt.controller;

import com.withgpt.gpt.model.User;
import com.withgpt.gpt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService; // UserService 주입

    @GetMapping("/users/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User()); // 새로운 User 객체 추가
        return "register"; // register.html 반환
    }

    @PostMapping("/users/register")
    public String registerUser(User user) {
        userService.registerUser(user); // 사용자 등록 처리
        return "redirect:/"; // 회원가입 후 메인 페이지로 리디렉션
    }
}
