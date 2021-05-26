package com.example.boardproject.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private int hit=0;
    private String title;
    private String content;


    public Post(String title, String content) {

        this.title = title;
        this.content = content;
    }

    public void setMember(Member member){
        this.member=member;
        member.getPostList().add(this);
    }

    public void addHit() {
        this.hit++;
    }
}