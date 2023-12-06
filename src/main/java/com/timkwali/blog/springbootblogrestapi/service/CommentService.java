package com.timkwali.blog.springbootblogrestapi.service;

import com.timkwali.blog.springbootblogrestapi.payload.CommentDto;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
}
