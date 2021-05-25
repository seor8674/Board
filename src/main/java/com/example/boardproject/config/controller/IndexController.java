package com.example.boardproject.config.controller;

import com.example.boardproject.config.auth.PrincipalDetails;
import com.example.boardproject.entity.Member;
import com.example.boardproject.repository.MemberRepository;
import com.example.boardproject.repository.PostRepository;
import com.example.boardproject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PostRepository  postRepository;



    @GetMapping("/")
    public String home(@AuthenticationPrincipal PrincipalDetails userDetails, Model model){
        try{
            model.addAttribute("name",userDetails.getMember().getUsername());
            model.addAttribute("check",true);
        }catch (NullPointerException e){
            model.addAttribute("check",false);
        }
        model.addAttribute("post",postRepository.findTop10By());


        return "index";

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
    @GetMapping("/member/enter")
    public String enter(){
        return "register";
    }
}
