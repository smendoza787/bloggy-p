package com.smendoza.bloggy.ws;

import com.smendoza.bloggy.svc.BlogPostService;
import com.smendoza.bloggy.svc.model.BlogPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
public class BlogPostController {

    private static Logger log = LoggerFactory.getLogger(BlogPostController.class);

    private final BlogPostService service;

    @Inject
    public BlogPostController(BlogPostService service) {
        this.service = service;
    }

    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPostDto postBlogPost(@Valid @RequestBody BlogPostDto entity) {

        log.info("CONTROLLER: POST Blog Post with title: {}", entity.title);

        BlogPost result = service.createBlogPost(entity.toBlogPost());

        return BlogPostDto.fromBlogPost(result);
    }

    @RequestMapping(value = "/posts/{blogPostId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public BlogPostDto getBlogPost(@PathVariable String blogPostId) {

        log.info("CONTROLLER: GET Blog Post with id: {}", blogPostId);

        BlogPost result = service.getBlogPostById(blogPostId);

        return BlogPostDto.fromBlogPost(result);
    }

    @RequestMapping(value = "/posts/{blogPostId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public BlogPostDto putBlogPost(@PathVariable String blogPostId, @Valid @RequestBody BlogPostDto entity) {

        log.info("CONTROLLER: PUT Blog Post with id: {}", blogPostId);

        if (entity.id != null && entity.id != blogPostId) {
            throw new RuntimeException("PathVariable blogPostId and entity.id do not match");
        }

        entity.id = blogPostId;

        BlogPost result = service.updateBlogPost(entity.toBlogPost());

        return BlogPostDto.fromBlogPost(result);
    }

    @RequestMapping(value = "/posts/{blogPostId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public String deleteBlogPost(@PathVariable String blogPostId) {

        log.info("CONTROLLER: DELETE Blog Post with id: {}", blogPostId);

        Boolean result = service.deleteBlogPostById(blogPostId);

        if (result) {
            return "Blog Post with id: {" + blogPostId + "} was successfully deleted.";
        }

        throw new EntityNotFoundException("Error deleting Blog Post with id: " + blogPostId);
    }
}
