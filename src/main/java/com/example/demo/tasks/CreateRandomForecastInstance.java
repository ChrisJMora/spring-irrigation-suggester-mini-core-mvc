package com.example.demo.tasks;

import com.example.demo.dto.ForecastDTO;
import com.example.demo.dto.LocationDTO;
import com.example.demo.exception.EmptyRecordException;
import com.example.demo.model.agriculture.Forecast;
import com.example.demo.model.agriculture.Location;
import com.example.demo.service.ForecastService;
import com.example.demo.service.LocationService;
import com.example.demo.utils.mapper.ForecastMapper;
import com.example.demo.utils.mapper.LocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class CreateRandomForecastInstance {

    @Autowired
    LocationService locationService;

    @Autowired
    ForecastService forecastService;

    @Scheduled(fixedRate = 30000) // Every 30 seconds
    public void CreateInstance() {
        Forecast todayForecast;
        try {
            todayForecast = forecastService.getForecastFromToday();
            todayForecast.setRandomPrecipitation();
        } catch (EmptyRecordException ex) {
            todayForecast = new Forecast(locationService.getRandomLocation());
        }
        forecastService.saveForecast(todayForecast);
    }
}
