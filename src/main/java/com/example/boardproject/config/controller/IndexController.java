package com.example.boardproject.config.controller;

import com.example.boardproject.config.auth.PrincipalDetails;
import com.example.boardproject.entity.Member;
import com.example.boardproject.entity.Post;
import com.example.boardproject.repository.MemberRepository;
import com.example.boardproject.repository.PostRepository;
import com.example.boardproject.service.MemberService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Post> all = postRepository.findAll(pageRequest);
        List<Post> content = all.getContent();
        model.addAttribute("post",content);
        if(all.getTotalPages()==0){
            model.addAttribute("count",1);
        }
        else{
            model.addAttribute("count",all.getTotalPages());
        }



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




    @GetMapping("/board")
    public String page(@AuthenticationPrincipal PrincipalDetails userDetails,@RequestParam int page,Model model){
        try{
            model.addAttribute("name",userDetails.getMember().getUsername());
            model.addAttribute("check",true);
        }catch (NullPointerException e){
            model.addAttribute("check",false);
        }
        PageRequest pageRequest = PageRequest.of(page-1, 10);
        Page<Post> all = postRepository.findAll(pageRequest);
        List<Post> content = all.getContent();
        model.addAttribute("page",page);
        model.addAttribute("post",content);
        model.addAttribute("count",all.getTotalPages());

        return "page";

    }
    @GetMapping("/search")
    public String search(@AuthenticationPrincipal PrincipalDetails userDetails,String search, Model model){
        try{
            model.addAttribute("name",userDetails.getMember().getUsername());
            model.addAttribute("check",true);
        }catch (NullPointerException e){
            model.addAttribute("check",false);
        }
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Post> byTitleContaining = postRepository.findByTitleContaining(search, pageRequest);
        List<Post> content = byTitleContaining.getContent();
        model.addAttribute("search",search);
        model.addAttribute("post",content);
        model.addAttribute("count",byTitleContaining.getTotalPages());

        return "search";
    }
    @GetMapping("/search/board")
    public String searchpage(@AuthenticationPrincipal PrincipalDetails userDetails,int page,Model model,String search){
        try{
            model.addAttribute("name",userDetails.getMember().getUsername());
            model.addAttribute("check",true);
        }catch (NullPointerException e){
            model.addAttribute("check",false);
        }
        PageRequest pageRequest = PageRequest.of(page-1, 10);
        Page<Post> all = postRepository.findByTitleContaining(search,pageRequest);
        List<Post> content = all.getContent();
        model.addAttribute("search",search);
        model.addAttribute("page",page);
        model.addAttribute("post",content);
        model.addAttribute(
                "count",all.getTotalPages());

        return "searchpage";

    }

}
