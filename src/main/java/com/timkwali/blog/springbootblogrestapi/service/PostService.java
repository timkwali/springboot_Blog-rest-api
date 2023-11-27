package com.timkwali.blog.springbootblogrestapi.service;

import com.timkwali.blog.springbootblogrestapi.payload.PostDto;

public interface PostService {
    PostDto createPostDto(PostDto postDto);
}
