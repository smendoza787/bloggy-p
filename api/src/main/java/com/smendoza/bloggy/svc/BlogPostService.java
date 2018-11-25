package com.smendoza.bloggy.svc;

import com.smendoza.bloggy.svc.model.BlogPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
public class BlogPostService {

    private static Logger log = LoggerFactory.getLogger(BlogPostService.class);

    private final BlogPostRepository repository;

    @Inject
    public BlogPostService(BlogPostRepository repository) {
        this.repository = repository;
    }

    public BlogPost createBlogPost(BlogPost entity) {

        log.info("SERVICE: Creating Blog Post with title: {}", entity.getTitle());

        entity.setId(UUID.randomUUID().toString());
        repository.save(entity);

        return repository.findById(entity.getId())
                .orElseThrow(() -> new EntityNotFoundException("Blog Post with id: " + entity.getId()));
    }

    public BlogPost getBlogPostById(String blogPostId) {

        log.info("SERVICE: Getting Blog Post with id: {}", blogPostId);

        return repository.findById(blogPostId)
                .orElseThrow(() -> new EntityNotFoundException("Blog Post with id: " + blogPostId));
    }

    public BlogPost updateBlogPost(BlogPost entity) {

        log.info("SERVICE: Updating Blog Post with id: {}", entity.getId());

        Optional<BlogPost> oldBlogPost = repository.findById(entity.getId());

        if (oldBlogPost.isPresent()) {
            return repository.save(entity);
        } else {
            throw new EntityNotFoundException("Blog Post with id: " + entity.getId() + " was not found.");
        }
    }
}
