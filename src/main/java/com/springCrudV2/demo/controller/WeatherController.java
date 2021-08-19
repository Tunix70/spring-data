package com.springCrudV2.demo.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.springCrudV2.demo.model.Main;
import com.springCrudV2.demo.model.Weather;
import com.springCrudV2.demo.service.WeatherService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.lang.reflect.Type;

@RestController
@RequestMapping(value = "/weather")
public class WeatherController {
    private final WeatherService weatherService;

    String URI = "http://api.openweathermap.org/data/2.5/weather?q=tomsk&appid=aa3c69683b82af8d25510259d657156d";

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;

    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('developers:read')")
    public ResponseEntity<Weather> getWeather() {
        RestTemplate restTemplate = new RestTemplate();
        String weatherString = restTemplate.getForObject(URI, String.class);

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();


        return weather != null
                ? new ResponseEntity<>(weather, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
