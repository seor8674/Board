package com.example.boardproject.config.controller;

import com.example.boardproject.config.auth.PrincipalDetails;
import com.example.boardproject.entity.Member;
import com.example.boardproject.entity.Post;
import com.example.boardproject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/member/enter")
    public String enter(){
        return "register";
    }


    @GetMapping("/member/mine")
    public String mine(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model){
        try{
            model.addAttribute("name",principalDetails.getMember().getUsername());
            model.addAttribute("check",true);
        }catch (NullPointerException e){
            model.addAttribute("check",false);
        }
        String username = principalDetails.getUsername();
        Member mine = memberService.mine(username);
        List<Post> postList = mine.getPostList();
        model.addAttribute("post",postList);
        model.addAttribute("size",(postList.size()/10)+1);

        return "minepage";
    }
    @GetMapping("/member/update")
    public String getupdate(@RequestParam Long id, Model model){
        model.addAttribute("id",id);
        return "mineupdate";
    }
    @PostMapping("/member/update")
    public String update(@RequestParam Long id,@RequestParam String title,@RequestParam String content){
        memberService.update(id,title,content);
        return "redirect:/member/mine";
    }
    @GetMapping("/member/delete")
    public String delete(@RequestParam Long id, Model model){
        memberService.delete(id);
        return "redirect:/member/mine";
    }
}
