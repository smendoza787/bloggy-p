package com.smendoza.bloggy.ws;

import com.smendoza.bloggy.svc.BlogPostService;
import com.smendoza.bloggy.svc.model.BlogPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

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
    public BlogPostDto postBlogPost(@RequestBody BlogPostDto entity) {

        log.info("CONTROLLER: Creating Blog Post with title: {}", entity.title);

        BlogPost result = service.createBlogPost(entity.toBlogPost());

        return BlogPostDto.fromBlogPost(result);
    }

    @RequestMapping(value = "/posts/{blogPostId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public BlogPostDto getBlogPost(@PathVariable String blogPostId) {

        log.info("CONTROLLER: Getting Blog Post with id: {}", blogPostId);

        BlogPost result = service.getBlogPostById(blogPostId);

        return BlogPostDto.fromBlogPost(result);
    }
}
