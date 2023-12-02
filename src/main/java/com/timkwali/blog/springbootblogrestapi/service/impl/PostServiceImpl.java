package com.timkwali.blog.springbootblogrestapi.service.impl;

import com.timkwali.blog.springbootblogrestapi.entity.Post;
import com.timkwali.blog.springbootblogrestapi.exception.ResourceNotFound;
import com.timkwali.blog.springbootblogrestapi.payload.PostDto;
import com.timkwali.blog.springbootblogrestapi.payload.PostResponse;
import com.timkwali.blog.springbootblogrestapi.repository.PostRepository;
import com.timkwali.blog.springbootblogrestapi.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPostDto(PostDto postDto) {
        //convert DTO to entity
        Post post = mapToEntity(postDto);

        Post newPost = postRepository.save(post);

        //convert entity to DTO to return response to client
        PostDto postResponse = mapToDto(newPost);

        return postResponse;
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize) {
        //create pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Post> allPosts = postRepository.findAll(pageable);

        //get content from page object
        List<Post> postsList = allPosts.getContent();

        List<PostDto> content = postsList.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
       
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(allPosts.getNumber());
        postResponse.setPageSize(allPosts.getSize());
        postResponse.setTotalElements(allPosts.getTotalElements());
        postResponse.setTotalPages(allPosts.getTotalPages());
        postResponse.setLast(allPosts.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Post", "id", id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        //get post by id from database
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Post", "id", id));

        //update post
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        //replace updated post in database
        Post updatedPost = postRepository.save(post);

        //return updated PostDto
        return mapToDto(updatedPost);
    }

    @Override
    public void deletePostById (long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Post", "id", id));
        postRepository.delete(post);
    }

    //Convert entity to DTO
    private PostDto mapToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }

    //Convert DTO to entity
    private Post mapToEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }

}
