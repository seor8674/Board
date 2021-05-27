package com.example.boardproject.service;


import com.example.boardproject.entity.Member;
import com.example.boardproject.entity.Post;
import com.example.boardproject.repository.MemberRepository;
import com.example.boardproject.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PostRepository postRepository;

    public Long join(Member member){
        String pwd=member.getPassword();
        String enpwd=bCryptPasswordEncoder.encode(pwd);
        member.setPassword(enpwd);
        member.setProviderid("local");
        member.setProvider("local");
        memberRepository.save(member);
        return member.getId();
    }

    public Member mine(String username){
        Member byuserid = memberRepository.findByuserid(username);
        return byuserid;

    }
    public void update(Long id,String title,String content){
        Post post = postRepository.findById(id).get();
        post.setContent(content);
        post.setTitle(title);
    }

    public void delete(Long id){
        postRepository.deleteById(id);
    }
}
