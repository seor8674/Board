package com.example.boardproject.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String userid;
    private String password;

    private String email;

    private String Role;

    @OneToMany(mappedBy = "member")
    List<Post> postList = new ArrayList<>();

    public Member(String username, String userid, String password, String email) {
        this.username = username;
        this.userid = userid;
        this.password = password;
        this.email = email;
        if(userid.contains("admin")){
            this.Role= "ROLE_ADMIN";
        }
        else{
            this.Role="ROLE_MEMBER";
        }
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
