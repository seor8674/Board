package com.example.boardproject.config.auth;

import com.example.boardproject.entity.Member;
import com.example.boardproject.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    MemberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
        Member member = memberRepository.findByuserid(userid);
        if(member!=null){
            return new PrincpalDetails(member);
        }
        return null;
    }
}
