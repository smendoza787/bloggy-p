package com.smendoza.bloggy.ws;

import com.smendoza.bloggy.svc.model.BlogPost;
import com.smendoza.bloggy.svc.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.MissingResourceException;

@RestController
public class BlogPostController {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @GetMapping("/posts")
    public Page<BlogPost> getBlogPosts(Pageable pageable) {
        return blogPostRepository.findAll(pageable);
    }

    @PostMapping("/posts")
    public BlogPost createBlogPost(@Valid @RequestBody BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }

    @PutMapping("/posts/{blogPostId}")
    public BlogPost updateBlogPost(@PathVariable Long blogPostId, @Valid @RequestBody BlogPost blogPostRequest) {
        return blogPostRepository.findById(blogPostId)
                .map(blogPost -> {
                    blogPost.setTitle(blogPostRequest.getTitle());
                    blogPost.setContent(blogPostRequest.getContent());
                    return blogPostRepository.save(blogPost);
                }).orElseThrow(() -> new EntityNotFoundException("Blog Post with id: " + blogPostId));
    }

    @DeleteMapping("/posts/{blogPostId}")
    public ResponseEntity<?> deleteBlogPost(@PathVariable Long blogPostId) {
        return blogPostRepository.findById(blogPostId)
                .map(blogPost -> {
                    blogPostRepository.delete(blogPost);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new EntityNotFoundException("Blog Post with id: " + blogPostId));
    }
}
