package com.example.casestudy.controller;

import com.example.casestudy.model.Bus;
import com.example.casestudy.repositry.UserRepositry;
import com.example.casestudy.dao.BusDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BusController {

    @Autowired
    private UserRepositry userRepositry;

    @Autowired
    private BusDao busDao;

    @GetMapping("/buses")
    public ResponseEntity<List<Bus>> getAllBuses() {
        List<Bus> buses = busDao.getAllBuses();
        return ResponseEntity.ok(buses);
    }

    @GetMapping("/bus/details/{id}")
    public ResponseEntity<Bus> getBusDetails(@PathVariable int id) {
        Bus bus = busDao.getBusDetails(id);
        return ResponseEntity.of(bus);
    }

    @GetMapping("/bus/timing_details/{id}")
    public ResponseEntity<String> getBusTimeDetails(@PathVariable int id) {
        String timingDetails = busDao.getBusTimeDetails(id);
        return ResponseEntity.of(timingDetails);
    }

    @PostMapping("/bus/{user_id}/create")
    public ResponseEntity<String> createBusEntry(@PathVariable("user_id") int user_id, @RequestBody Bus bus) {
        if (!userRepositry.findById(user_id).map(user -> "admin".equals(user.getRole())).orElse(false)) {
            return ResponseEntity.status(403).body("Access restricted");
        }
        busDao.createBusEntry(bus);
        return ResponseEntity.status(201).body("New bus entry created");
    }

    @PutMapping("/bus/{user_id}/update_details")
    public ResponseEntity<Object> updateBusDetails(@PathVariable("user_id") int user_id, @RequestBody Bus bus) {
        if (!userRepositry.findById(user_id).map(user -> "admin".equals(user.getRole())).orElse(false)) {
            return ResponseEntity.status(403).body("Access restricted");
        }
        ResponseEntity<Object> response = busDao.updateBusDetails(bus);
        return response;
    }

    @DeleteMapping("/bus/{user_id}/delete/{id}")
    public ResponseEntity<String> deleteBus(@PathVariable("user_id") int user_id, @PathVariable("id") int id) {
        if (!userRepositry.findById(user_id).map(user -> "admin".equals(user.getRole())).orElse(false)) {
            return ResponseEntity.status(403).body("Access restricted");
        }
        ResponseEntity<String> response = busDao.deleteBus(id);
        return response;
    }
}