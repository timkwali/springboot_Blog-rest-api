package com.timkwali.blog.springbootblogrestapi.repository;

import com.timkwali.blog.springbootblogrestapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Long> {
}
