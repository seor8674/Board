package com.example.boardproject.repository;

import com.example.boardproject.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {



    public List<Post> findTop3By();

    public List<Post> findTop10By();
}
