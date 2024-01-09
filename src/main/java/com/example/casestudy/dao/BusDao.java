package com.example.casestudy.dao;

import com.example.casestudy.model.Bus;
import com.example.casestudy.repositry.BusRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BusDao {

    @Autowired
    private BusRepositry busRepositry;

    public List<Bus> getAllBuses() {
        return busRepositry.findAll();
    }

    public Bus getBusDetails(int id) {
        return busRepositry.findById(id).orElse(null);
    }

    public String getBusTimeDetails(int id) {
        Optional<Bus> bus = busRepositry.findById(id);
        return bus.map(b -> b.getDepartureTime() + " to " + b.getArrivalTime()).orElse(null);
    }

    public void createBusEntry(Bus bus) {
        busRepositry.save(bus);
    }

    public ResponseEntity<Object> updateBusDetails(Bus bus) {
        Optional<Bus> existingBus = busRepositry.findById(bus.getBusId());
        if (existingBus.isPresent()) {
            busRepositry.save(bus);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<String> deleteBus(int id) {
        Optional<Bus> existingBus = busRepositry.findById(id);
        if (existingBus.isPresent()) {
            busRepositry.deleteById(id);
            return ResponseEntity.ok("Deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}