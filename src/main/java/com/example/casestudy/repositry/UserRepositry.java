package com.example.casestudy.repositry;

import com.example.casestudy.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepositry extends CrudRepository<User,Integer> {

    User findById(int id);
    Iterable<User> findAll();
    void deleteById(int id);
}
