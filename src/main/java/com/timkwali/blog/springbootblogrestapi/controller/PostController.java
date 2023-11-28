package com.timkwali.blog.springbootblogrestapi.controller;

import com.timkwali.blog.springbootblogrestapi.payload.PostDto;
import com.timkwali.blog.springbootblogrestapi.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //create blog post
    @PostMapping
    public ResponseEntity<PostDto> createPost(
            @RequestBody PostDto postDto
    ) {
        return new ResponseEntity<>(
                postService.createPostDto(postDto),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return new ResponseEntity<>(
                postService.getAllPosts(),
                HttpStatus.OK
        );
    }
}
