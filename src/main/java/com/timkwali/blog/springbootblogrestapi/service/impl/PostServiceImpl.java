package com.timkwali.blog.springbootblogrestapi.service.impl;

import com.timkwali.blog.springbootblogrestapi.entity.Post;
import com.timkwali.blog.springbootblogrestapi.payload.PostDto;
import com.timkwali.blog.springbootblogrestapi.repository.PostRepository;
import com.timkwali.blog.springbootblogrestapi.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPostDto(PostDto postDto) {
        //convert DTO to entity
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post newPost = postRepository.save(post);

        //convert entity to DTO to return response to client
        PostDto postResponse = new PostDto();
        postResponse.setId(newPost.getId());
        postResponse.setTitle(newPost.getTitle());
        postResponse.setDescription(newPost.getDescription());
        postResponse.setContent(newPost.getContent());

        return postResponse;
    }
}
