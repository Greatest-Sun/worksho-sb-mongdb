package com.gabrieldb.mongodb.repositories;

import com.gabrieldb.mongodb.domain.Post;
import com.gabrieldb.mongodb.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
}
