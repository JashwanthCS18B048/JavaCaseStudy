package com.example.casestudy.dao;

import com.example.casestudy.model.User;
import com.example.casestudy.repositry.UserRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDao {

    @Autowired
    private UserRepositry userRepositry;

    public Iterable<User> getAllUsers() {
        return userRepositry.findAll();
    }

    public Optional<User> getUserDetails(int id) {
        return userRepositry.findById(id);
    }

    public User createUserEntry(User user) {
        return userRepositry.save(user);
    }

    public ResponseEntity<Object> updateUserDetails(User user) {
        return userRepositry.findById(user.getUserId())
                .map(existingUser -> {
                    userRepositry.save(user);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
    public ResponseEntity<String> deleteUser(int id) {
        return userRepositry.findById(id)
                .map(existingUser -> {
                    userRepositry.deleteById(id);
                    return ResponseEntity.ok("Deleted");
                })
                .orElse(ResponseEntity.notFound().build());
    }
}