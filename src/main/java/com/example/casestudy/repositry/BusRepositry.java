package com.example.casestudy.repositry;


import com.example.casestudy.model.Bus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepositry extends CrudRepository<Bus,Integer> {
    // custom query to search to blog post by title or content
    Bus findById(int id);
    List<Bus> findAll();
    void deleteById(int id);

}
