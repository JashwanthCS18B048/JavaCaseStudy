package com.example.casestudy.repositry;

import com.example.casestudy.model.Route;
import org.springframework.data.repository.CrudRepository;

public interface RouteRepositry  extends CrudRepository<Route,Integer> {
    // custom query to search to blog post by title or content
    Route findById(int id);
    Iterable<Route> findAll();
    void deleteById(int id);
}
