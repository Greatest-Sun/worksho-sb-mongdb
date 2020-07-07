package com.gabrieldb.mongodb.services;

import com.gabrieldb.mongodb.domain.User;
import com.gabrieldb.mongodb.dtos.UserDTO;
import com.gabrieldb.mongodb.repositories.UserRepository;
import com.gabrieldb.mongodb.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository ur;

    public List<User> findAll() {
        return ur.findAll();
    }
    
    public User findById(String id) {
        Optional<User> user = ur.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }

    public User insert(User obj) {
        return ur.insert(obj);
    }

    public void delete(String id) {
        ur.delete(findById(id));
    }

    public User update(User user) {
        User newuser = findById(user.getId());
        updateData(newuser, user);
        return ur.save(newuser);
    }

    private void updateData(User newuser, User user) {
        newuser.setName(user.getName());
        newuser.setEmail(user.getEmail());
    }

    public User fromDTO(UserDTO objDTO) {
        return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
    }

}