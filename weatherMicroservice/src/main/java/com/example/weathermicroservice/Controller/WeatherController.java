package com.example.weathermicroservice.Controller;

import com.example.weathermicroservice.Model.Main;
import com.example.weathermicroservice.Model.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

@RestController
public class WeatherController {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${appid}")
    private String appId;
    @Value("${url.weather}")
    private String urlWeather;
    @Autowired
    private CacheManager cacheManager;

    @GetMapping("/cache")
    public Map<Object, Object> getCache() {
        ConcurrentMapCache cache = (ConcurrentMapCache) cacheManager.getCache("weather");
        if (cache != null) {
            ConcurrentMap<Object, Object> map = cache.getNativeCache();
            return map;
        } else {
            return Map.of();
        }
    }
    @Cacheable(value = "weather", key = "#lat.concat(':').concat(#lon)")
    @GetMapping("/weather")
    public Main getWeather(@RequestParam String lat, @RequestParam String lon) {
        String request = String.format("%s?lat=%s&lon=%s&units=metric&appid=%s",
                urlWeather, lat, lon, appId);
        return restTemplate.getForObject(request, Root.class).getMain();
    }
    @Scheduled(fixedRate = 60000)
    @CacheEvict(value = "weather", allEntries = true)
    public void cacheEvict() {
    }
}