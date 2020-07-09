package com.gabrieldb.mongodb.services;

import com.gabrieldb.mongodb.domain.Post;
import com.gabrieldb.mongodb.domain.User;
import com.gabrieldb.mongodb.dtos.UserDTO;
import com.gabrieldb.mongodb.repositories.PostRepository;
import com.gabrieldb.mongodb.repositories.UserRepository;
import com.gabrieldb.mongodb.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository pr;

    public List<Post> findAll() {
        return pr.findAll();
    }

    public Post findById(String id) {
        Optional<Post> post = pr.findById(id);
        return post.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }

    public List<Post> findByTitle(String text) {
        return pr.findByTitle(text);
    }

    public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
        return pr.fullSearch(text, minDate, maxDate);
    }
}