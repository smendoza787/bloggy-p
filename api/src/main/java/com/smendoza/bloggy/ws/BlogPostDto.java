package com.smendoza.bloggy.ws;

import com.smendoza.bloggy.svc.model.BlogPost;

public class BlogPostDto {

    public String id;
    public String title;
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
