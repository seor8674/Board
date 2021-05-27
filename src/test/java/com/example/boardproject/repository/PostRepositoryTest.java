package com.example.boardproject.repository;

import com.example.boardproject.entity.Member;
import com.example.boardproject.entity.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostRepositoryTest {
    @Autowired PostRepository postRepository;
    @Autowired MemberRepository memberRepository;


    @Test
    public void postTest(){
        Member member = new Member("MemberA","as","123","qwd");
        memberRepository.save(member);
        Post post1 = new Post("A","contentA");
        Post post2 = new Post("B","contentB");
        Post post3 = new Post("C","contentC");
        Post post4 = new Post("C","contentC");
        Post post5 = new Post("C","contentC");
        Post post6 = new Post("C","contentC");

        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);
        postRepository.save(post4);
        postRepository.save(post5);
        postRepository.save(post6);

        //Post firstBy = postRepository.findFirstByOrderById();


    }
    @Test
    public void page(){
        Member member = new Member("MemberA","as","123","qwd");
        memberRepository.save(member);
        Post post1 = new Post("A","contentA");
        Post post2 = new Post("B","contentB");
        Post post3 = new Post("C","contentC");
        Post post4 = new Post("C","contentC");
        Post post5 = new Post("C","contentC");
        Post post6 = new Post("C","contentC");

        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);
        postRepository.save(post4);
        postRepository.save(post5);
        postRepository.save(post6);

        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Post> all = postRepository.findAll(pageRequest);
        System.out.println(all.getContent().size());
        System.out.println(all.getTotalElements());
        System.out.println(all.getNumber());
        System.out.println(all.getTotalPages());
        System.out.println(all.hasNext());
    }
    @Test
    public void like(){
        Member member = new Member("MemberA","as","123","qwd");
        memberRepository.save(member);
        Post post1 = new Post("Adidas","contentA");
        Post post2 = new Post("Beyond","contentB");
        Post post3 = new Post("Channel","contentC");
        Post post4 = new Post("Calheart","contentC");
        Post post5 = new Post("Nike","contentC");
        Post post6 = new Post("puma","contentC");

        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);
        postRepository.save(post4);
        postRepository.save(post5);
        postRepository.save(post6);
        List<Post> a = postRepository.findByTitleContaining("a");
        for (Post post : a) {
            System.out.println(post.getTitle());
        }

    }
}