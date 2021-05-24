package com.example.boardproject.config.controller;

import com.example.boardproject.entity.Member;
import com.example.boardproject.repository.MemberRepository;
import com.example.boardproject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;


    @GetMapping("/member")
    public String user(){
        return "member";
    }

    @GetMapping("/joinform")
    public String join(){
        return "joinform";
    }

    @GetMapping("/loginform")
    public String login(){
        return "loginform";
    }

    @PostMapping("/join")
    public String joinpro(Member member){
        memberService.join(member);
        return "redirect:/";
    }
}
