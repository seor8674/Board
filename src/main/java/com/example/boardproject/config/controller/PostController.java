package com.example.boardproject.config.controller;

import com.example.boardproject.config.auth.PrincipalDetails;
import com.example.boardproject.entity.Member;
import com.example.boardproject.entity.Post;
import com.example.boardproject.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {

    @Autowired
    PostRepository postRepository;

    @PostMapping("/register")
    public String register(@AuthenticationPrincipal PrincipalDetails principalDetails, Post post){
        Member member = principalDetails.getMember();
        post.setMember(member);
        postRepository.save(post);
        return "redirect:/";
    }




}
