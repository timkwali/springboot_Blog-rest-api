package com.timkwali.blog.springbootblogrestapi.controller;

import com.timkwali.blog.springbootblogrestapi.payload.PostDto;
import com.timkwali.blog.springbootblogrestapi.payload.PostResponse;
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
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        return new ResponseEntity<>(
                postService.getAllPosts(pageNo, pageSize, sortBy, sortDir),
                HttpStatus.OK
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long postId) {
        return new ResponseEntity<>(
                postService.getPostById(postId),
                HttpStatus.OK
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<PostDto> updatePost(
            @RequestBody PostDto postDto,
            @PathVariable("id") long postId
    ) {
        return ResponseEntity.ok(postService.updatePost(postDto, postId));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePostById(@PathVariable("id") long postId) {
        postService.deletePostById(postId);
        return ResponseEntity.ok("Post entity deleted successfully");
    }
}
