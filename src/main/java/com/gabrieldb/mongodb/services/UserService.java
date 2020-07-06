package com.gabrieldb.mongodb.services;

import com.gabrieldb.mongodb.domain.User;
import com.gabrieldb.mongodb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository ur;

    public List<User> findAll() {
        return ur.findAll();
    }
}
