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

    public Weather getWeatherMain(String username) {
        String city = getCity(username);
        String apiKey = "f0d7c77842669fcea2015fdeb04698ea";

        HttpUrl uri = new HttpUrl.Builder()
                .scheme("https")
                .host("api.openweathermap.org")
                .addPathSegment("data")
                .addPathSegment("2.5")
                .addPathSegment("weather")
                .addQueryParameter("q", city)
                .addQueryParameter("appid", apiKey)
                .build();
        Request request = new Request.Builder().url(uri).get().build();
        Response response = getResponce(request);
        return getWeatherFromResponce(response);
    }

    public String getCity(String userName) {
        return userRepository.getUserByName(userName).orElseThrow(
                () -> new UsernameNotFoundException(userName)).getCity();
    }

    public Response getResponce (Request request) {
        Response response = null;
        OkHttpClient client = new OkHttpClient();
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public Weather getWeatherFromResponce (Response response) {
        Weather weather = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            weather = mapper.readValue(response.body().string(), Weather.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weather;
    }
 }
