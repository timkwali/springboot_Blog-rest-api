package com.timkwali.blog.springbootblogrestapi.service;

import com.timkwali.blog.springbootblogrestapi.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPostDto(PostDto postDto);

    List<PostDto> getAllPosts();
}
