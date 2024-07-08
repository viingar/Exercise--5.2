package com.example.locationmicroservice.Controller;

import com.example.locationmicroservice.Model.Location;
import com.example.locationmicroservice.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Service
@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private LocationRepository repository;

    @GetMapping
    public Iterable<Location> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Location> findById(@PathVariable int id) {
        return repository.findById(id);
    }

    @PostMapping
    public ResponseEntity<Location> save(@RequestBody Location location) {
        return repository.findById(location.getId()).isPresent()
                ? new ResponseEntity(repository.findById(location.getId()), HttpStatus.BAD_REQUEST)
                : new ResponseEntity(repository.save(location), HttpStatus.CREATED);
    }
}