package com.example.casestudy.controller;

import com.example.casestudy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.casestudy.dao.UserDao;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping
    public ResponseEntity<Iterable<User>> getAllUsers() {
        return ResponseEntity.ok(userDao.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserDetails(@PathVariable int id) {
        return ResponseEntity.of(userDao.getUserDetails(id));
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUserEntry(@RequestBody User user) {
        return ResponseEntity.status(201).body(userDao.createUserEntry(user));
    }

    @PutMapping("/update_details")
    public ResponseEntity<Object> updateUserDetails(@RequestBody User user) {
        return userDao.updateUserDetails(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        return userDao.deleteUser(id);
    }
}