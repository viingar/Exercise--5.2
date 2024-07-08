package com.example.weathermicroservice.Controller;

import com.example.weathermicroservice.Model.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class WeatherController {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${appid}")
    private String appId;

    @GetMapping("/weather")
    public Root getWeather() {
        return restTemplate.getForObject("https://api.openweathermap.org/data/2.5/weather?lat=54.1838&lon=45.1749&units=metric&appid=" + appId, Root.class);
    }
}