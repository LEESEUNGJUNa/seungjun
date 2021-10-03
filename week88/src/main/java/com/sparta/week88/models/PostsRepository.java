package com.sparta.week88.models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long> {
    List<Posts> findAllByOrderByCreatedAtDesc();
}
