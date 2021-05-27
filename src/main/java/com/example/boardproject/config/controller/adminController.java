package com.example.boardproject.config.controller;

import com.example.boardproject.config.auth.PrincipalDetails;
import com.example.boardproject.entity.Member;
import com.example.boardproject.entity.Post;
import com.example.boardproject.repository.PostRepository;
import com.example.boardproject.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class adminController {

    @Autowired
    AdminService adminService;
    @Autowired
    PostRepository postRepository;

    @GetMapping("/admin")
    public String admin(@AuthenticationPrincipal PrincipalDetails userDetails, Model model){
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
        model.addAttribute("count",all.getTotalPages());
        return "admin";
    }

    @GetMapping("/admin/update")
    public String getupdate(@RequestParam Long id, Model model){
        model.addAttribute("id",id);
        return "update";
    }

    @PostMapping("/admin/update")
    public String update(@RequestParam Long id,@RequestParam String title,@RequestParam String content){
        adminService.update(id,title,content);
        return "redirect:/";
    }
    @GetMapping("/admin/delete")
    public String delete(@RequestParam Long id, Model model){
        adminService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/admin/board")
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

        return "adminpage";

    }

}
