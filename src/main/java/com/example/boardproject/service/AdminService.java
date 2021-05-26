package com.example.boardproject.service;

import com.example.boardproject.entity.Post;
import com.example.boardproject.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminService {

    @Autowired
    PostRepository postRepository;


    public void update(Long id,String title,String content){
        Post post = postRepository.findById(id).get();
        post.setContent(content);
        post.setTitle(title);
    }



}
