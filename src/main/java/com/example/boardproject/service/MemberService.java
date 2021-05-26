package com.example.boardproject.service;


import com.example.boardproject.entity.Member;
import com.example.boardproject.repository.MemberRepository;
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

    public Long join(Member member){
        String pwd=member.getPassword();
        String enpwd=bCryptPasswordEncoder.encode(pwd);
        member.setPassword(enpwd);
        member.setProviderid("local");
        member.setProvider("local");
        memberRepository.save(member);
        return member.getId();
    }
}
