package com.example.boardproject.config.controller;

import com.example.boardproject.config.auth.PrincipalDetails;
import com.example.boardproject.entity.Member;
import com.example.boardproject.entity.Post;
import com.example.boardproject.repository.PostRepository;
import com.example.boardproject.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class adminController {

    @Autowired
    AdminService adminService;

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
}
