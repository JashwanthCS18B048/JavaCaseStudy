package com.example.casestudy.controller;

import com.example.casestudy.model.Route;
import com.example.casestudy.repositry.UserRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.casestudy.dao.RouteDao;

import java.util.Optional;

@RestController
public class RouteController {

    @Autowired
    private UserRepositry userRepositry;

    @Autowired
    private RouteDao routeDao;

    @GetMapping("/routes")
    public ResponseEntity<Iterable<Route>> getAllRoutes() {
        Iterable<Route> routes = routeDao.getAllRoutes();
        return ResponseEntity.ok(routes);
    }

    @GetMapping("/route/details/{id}")
    public ResponseEntity<Route> getRouteDetails(@PathVariable int id) {
        Route route = routeDao.getRouteDetails(id);
        return ResponseEntity.of(route);
    }

    @PostMapping("/route/create")
    public ResponseEntity<Route> createRouteEntry(@RequestBody Route route) {
        Route savedRoute = routeDao.createRouteEntry(route);
        return ResponseEntity.status(201).body(savedRoute);
    }

    @PutMapping("/route/{user_id}/update_details")
    public ResponseEntity<Object> updateRouteDetails(@PathVariable("user_id") int user_id, @RequestBody Route route) {
        if (!isAdmin(user_id)) {
            return ResponseEntity.status(403).body("Access restricted");
        }
        ResponseEntity<Object> response = routeDao.updateRouteDetails(route);
        return response;
    }

    @DeleteMapping("/route/{user_id}/delete/{id}")
    public ResponseEntity<String> deleteRoute(@PathVariable("user_id") int user_id, @PathVariable("id") int id) {
        if (!isAdmin(user_id)) {
            return ResponseEntity.status(403).body("Access restricted");
        }
        ResponseEntity<String> response = routeDao.deleteRoute(id);
        return response;
    }

    private boolean isAdmin(int user_id) {
        return userRepositry.findById(user_id).map(user -> "admin".equals(user.getRole())).orElse(false);
    }
}