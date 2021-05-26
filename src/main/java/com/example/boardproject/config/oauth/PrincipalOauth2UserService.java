package com.example.boardproject.config.oauth;

import com.example.boardproject.config.auth.PrincipalDetails;
import com.example.boardproject.config.oauth.provider.FacebookUserinfo;
import com.example.boardproject.config.oauth.provider.GoogleUserinfo;
import com.example.boardproject.config.oauth.provider.OAuth2Userinfo;
import com.example.boardproject.entity.Member;
import com.example.boardproject.repository.MemberRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service

public class PrincipalOauth2UserService extends DefaultOAuth2UserService {


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MemberRepository memberRepository;

    @Override//login 후처리 함수
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        //System.out.println(userRequest.getClientRegistration());
        //System.out.println(userRequest.getAccessToken().getTokenValue());
        //loaduser 함수로 회원정보 받을수있음

        OAuth2User oAuth2User = super.loadUser(userRequest);
        //System.out.println(oAuth2User.getAttributes());

        OAuth2Userinfo oAuth2Userinfo=null;
        if(userRequest.getClientRegistration().getRegistrationId().equals("google")){

            oAuth2Userinfo = new GoogleUserinfo(oAuth2User.getAttributes());

        }else if(userRequest.getClientRegistration().getRegistrationId().equals("facebook")){
            oAuth2Userinfo = new FacebookUserinfo(oAuth2User.getAttributes());

        }

        String provider = oAuth2Userinfo.getprovider();
        String providerid = oAuth2Userinfo.getproviderid();
        String email = oAuth2Userinfo.getemail();
        String userid = provider + "_" + providerid;
        String username = oAuth2Userinfo.getName();
        String password = bCryptPasswordEncoder.encode("이환준");//의미없음
        String Role = "ROLE_MEMBER";

        Member byuserid = memberRepository.findByuserid(userid);
        Hibernate.initialize(byuserid);
        if (byuserid == null) {
            byuserid.setProvider(provider);
            byuserid.setProviderid(providerid);
            memberRepository.save(byuserid);
        } else {

        }
        return new PrincipalDetails(byuserid,oAuth2User.getAttributes());

    }

}
