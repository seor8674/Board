package com.example.boardproject.config.controller;

import com.example.boardproject.config.auth.PrincipalDetails;
import com.example.boardproject.entity.Member;
import com.example.boardproject.entity.Post;
import com.example.boardproject.repository.MemberRepository;
import com.example.boardproject.repository.PostRepository;
import com.example.boardproject.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PostService postService;

    @PostMapping("/register")
    public String register(@AuthenticationPrincipal PrincipalDetails principalDetails, Post post){
        String username = principalDetails.getUsername();
        Member member = memberRepository.findByuserid(username);
        post.setMember(member);
        postRepository.save(post);
        return "redirect:/";
    }

    @GetMapping("/post/details")
    public String detail(@RequestParam Long id, Model model){
        Post post = postService.detail(id);
        model.addAttribute("title",post.getTitle());
        model.addAttribute("content",post.getContent());
        return "details";

    }








}
