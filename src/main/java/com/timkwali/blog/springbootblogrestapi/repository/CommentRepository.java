package com.timkwali.blog.springbootblogrestapi.repository;

import com.timkwali.blog.springbootblogrestapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
