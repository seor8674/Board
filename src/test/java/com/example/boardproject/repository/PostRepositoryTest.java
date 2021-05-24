package com.example.boardproject.repository;

import com.example.boardproject.entity.Member;
import com.example.boardproject.entity.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
        Post post1 = new Post(member,"A","contentA");
        Post post2 = new Post(member,"B","contentB");
        Post post3 = new Post(member,"C","contentC");
        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);
        List<Post> all = postRepository.findAll();
        Assertions.assertThat(all.get(2).getHit()).isEqualTo(0);
        Assertions.assertThat(all.size()).isEqualTo(3);
        Assertions.assertThat(all.get(1).getContent()).isEqualTo("contentB");
    }
}