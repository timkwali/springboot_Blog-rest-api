package com.timkwali.blog.springbootblogrestapi.service;

import com.timkwali.blog.springbootblogrestapi.payload.PostDto;
import com.timkwali.blog.springbootblogrestapi.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPostDto(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePostById(long id);
}
