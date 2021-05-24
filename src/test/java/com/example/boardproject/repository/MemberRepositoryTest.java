package com.example.boardproject.repository;

import com.example.boardproject.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@SpringBootTest
@Transactional
class MemberRepositoryTest {
    @Autowired MemberRepository memberRepository;

    @Test
    public void MemberTest(){
        Member member1 = new Member("MemberA","as1","123","qwd");
        Member member2 = new Member("MemberB","as2","123","qwd");
        Member member3 = new Member("MemberC","as3","123","qwd");
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        List<Member> all = memberRepository.findAll();
        Member as1 = memberRepository.findByuserid("as1");
        System.out.println("as1.getUserid() = " + as1.getUserid());
        Assertions.assertThat(all.size()).isEqualTo(3);
        Assertions.assertThat(all.get(0).getUsername()).isEqualTo("MemberA");
    }
}