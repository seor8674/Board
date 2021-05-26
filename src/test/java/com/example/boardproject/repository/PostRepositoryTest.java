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
        List<Post> top3By = postRepository.findTop3By();
        List<Post> top10By = postRepository.findTop10By();
        org.assertj.core.api.Assertions.assertThat(top3By.get(0).getTitle()).isEqualTo("A");
        System.out.println(top3By.size());
        System.out.println(top10By.size());
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
}