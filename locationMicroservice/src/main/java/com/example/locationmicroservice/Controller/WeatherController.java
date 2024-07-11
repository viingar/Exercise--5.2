package com.example.locationmicroservice.Controller;

import com.example.locationmicroservice.Model.Geodata;
import com.example.locationmicroservice.Model.Weather;
import com.example.locationmicroservice.Repository.GeodataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
public class WeatherController {

    @Autowired
    private GeodataRepository repository;
    private RestTemplate restTemplate;
    @Value("${weather.url}")
    String weatherUrl;
    @GetMapping("/weather")
    public ResponseEntity<?> redirectRequestWeather(@RequestParam String location) {
        Optional<Geodata> optionalGeodata = repository.findByName(location);

        if (!optionalGeodata.isPresent()) {
            return new ResponseEntity<>("Location not found", HttpStatus.NOT_FOUND);
        }

        Geodata geodata = optionalGeodata.get();
        String url = String.format("http://%s/?lat=%s&lon=%s", weatherUrl, geodata.getLat(), geodata.getLon());
        try {
            Weather weather = restTemplate.getForObject(url, Weather.class);
            return new ResponseEntity<>(weather, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error fetching weather data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/location")
    public List<Geodata> getAllLocations() {
        return (List<Geodata>) repository.findAll();
    }
    @GetMapping
    public Optional<Geodata> getWeather(@RequestParam String location) {
        return repository.findByName(location);
    }

    @GetMapping("/location/{name}")
    public Optional<Geodata> getLocationByName(@PathVariable String name) {
        return repository.findByName(name);
    }
    @PostMapping
    public Geodata save(@RequestBody Geodata geodata) {
        return repository.save(geodata);
    }
    @PutMapping("/location/{name}")
    public Geodata updateLocation(@PathVariable String name, @RequestBody Geodata geodata) {
        Optional<Geodata> existingGeodata = repository.findByName(name);
        if (existingGeodata.isPresent()) {
            Geodata updatedGeodata = existingGeodata.get();
            updatedGeodata.setLat(geodata.getLat());
            updatedGeodata.setLon(geodata.getLon());

            return repository.save(updatedGeodata);
        } else {

            return null;
        }
    }

    @DeleteMapping("/location/{name}")
    public void deleteLocation(@PathVariable String name) {
        repository.deleteByName(name);
    }
}