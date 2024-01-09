package com.example.casestudy.dao;

import com.example.casestudy.model.Route;
import com.example.casestudy.repositry.RouteRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RouteDao {

    @Autowired
    private RouteRepositry routeRepositry;

    public Iterable<Route> getAllRoutes() {
        return routeRepositry.findAll();
    }

    public Route getRouteDetails(int id) {
        return routeRepositry.findById(id).orElse(null);
    }

    public Route createRouteEntry(Route route) {
        return routeRepositry.save(route);
    }

    public ResponseEntity<Object> updateRouteDetails(Route route) {
        Optional<Route> existingRoute = routeRepositry.findById(route.getRouteId());
        if (existingRoute.isPresent()) {
            routeRepositry.save(route);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<String> deleteRoute(int id) {
        Optional<Route> existingRoute = routeRepositry.findById(id);
        if (existingRoute.isPresent()) {
            routeRepositry.deleteById(id);
            return ResponseEntity.ok("Deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}