package com.springCrudV2.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springCrudV2.demo.dao.UserRepository;
import com.springCrudV2.demo.model.weather.Weather;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WeatherService {
    private UserRepository userRepository;

    @Autowired
    public WeatherService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Weather getWeather(String username) {
        Weather weather = null;
        HttpUrl uri = getUri(username);
        Request request = new Request.Builder().url(uri).get().build();
        Response response = getWeatherResponse(request);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            weather = objectMapper.readValue(response.body().string(), Weather.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weather;
    }

    public HttpUrl getUri(String userName) {
        String city = getCity(userName);
        String apiKey = "f0d7c77842669fcea2015fdeb04698ea";

        return new HttpUrl.Builder()
                .scheme("https")
                .host("api.openweathermap.org")
                .addPathSegment("data")
                .addPathSegment("2.5")
                .addPathSegment("weather")
                .addQueryParameter("q", city)
                .addQueryParameter("appid", apiKey)
                .build();
    }

    public String getCity(String userName) {
        return userRepository.getUserByName(userName).orElseThrow(
                () -> new UsernameNotFoundException(userName)).getCity();
    }

    private Response getWeatherResponse (Request request) {
        Response response = null;
        OkHttpClient client = new OkHttpClient();
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
 }
