package com.example.boardproject.entity;

import com.example.boardproject.repository.MemberRepository;
import com.example.boardproject.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class PostTest {

    @Autowired
    PostRepository postRepository;
    @Autowired
    MemberRepository memberRepository;


    @Test
    public void test(){
        Member member = new Member("MemberA","as","123","qwd");
        memberRepository.save(member);
        Post post1 = new Post("A","contentA");
        Post post2 = new Post("B","contentB");
        Post post3 = new Post("C","contentC");
        post1.setMember(member);
        post2.setMember(member);
        post3.setMember(member);
        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);
        List<Post> postList = member.getPostList();
        for (Post p:postList
             ) {
            System.out.println("------------------");
            System.out.println(p.getCreatedDate());
            System.out.println("------------------");
        }
        post1.setContent("newcon");
        post2.setContent("newcon");
        post3.setContent("newcon");
        List<Post> postList1 = member.getPostList();
        for (Post p:postList1
        ) {
            System.out.println("------------------");
            System.out.println(p.getCreatedDate());
            System.out.println(p.getLastmodifiedDate());
            System.out.println("------------------");
        }


    }

}