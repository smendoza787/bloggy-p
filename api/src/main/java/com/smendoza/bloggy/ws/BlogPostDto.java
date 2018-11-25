package com.smendoza.bloggy.ws;

import com.smendoza.bloggy.svc.model.BlogPost;

import javax.validation.constraints.NotBlank;

public class BlogPostDto {

    public String id;

    @NotBlank
    public String title;

    @NotBlank
    public String content;

    public static BlogPostDto fromBlogPost(BlogPost bp) {
        BlogPostDto dto = new BlogPostDto();
        dto.id = bp.getId();
        dto.title = bp.getTitle();
        dto.content = bp.getContent();

        return dto;
    }

    public BlogPost toBlogPost() {
        return new BlogPost(id, title, content);
    }
}
