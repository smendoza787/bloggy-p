package com.smendoza.bloggy.svc;

import com.smendoza.bloggy.svc.model.BlogPost;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends CrudRepository<BlogPost, String> {
}
