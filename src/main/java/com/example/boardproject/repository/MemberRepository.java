package com.example.boardproject.repository;

import com.example.boardproject.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {

    public Member findByuserid(String userid);
}
