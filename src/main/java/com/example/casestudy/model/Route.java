package com.example.casestudy.model;

import jakarta.persistence.*;

@Entity
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String routeName;

    private int duration;

    public Route() {
        // Default constructor required for JPA
    }

    public Route(String routeName, int duration) {
        this.routeName = routeName;
        this.duration = duration;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    // Additional methods

    public int getRouteId() {
        return id;
    }
}