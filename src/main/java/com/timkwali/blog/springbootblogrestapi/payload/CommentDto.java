package com.timkwali.blog.springbootblogrestapi.payload;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CommentDto {
    private long id;
    private String name;
    private String email;
    private String body;
}
