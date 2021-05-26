package com.example.boardproject.service;

import com.example.boardproject.entity.Post;
import com.example.boardproject.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostService {

    @Autowired
    PostRepository postRepository;

    public Post detail(Long id){
        Post post = postRepository.findById(id).get();
        post.addHit();
        return post;
    }


}
