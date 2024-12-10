package com.example.demo.controller;

import com.example.demo.model.agriculture.Forecast;
import com.example.demo.model.httpResponse.ApiResult;
import com.example.demo.model.httpResponse.Error;
import com.example.demo.model.httpResponse.WrappedEntity;
import com.example.demo.service.ForecastService;
import com.example.demo.tasks.ForecastEmulator;
import com.example.demo.utils.mapper.ForecastMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/forecast")
public class ForecastController {

    @Autowired
    private ForecastService forecastService;
    @Autowired
    private ForecastEmulator forecastEmulator;
    @Autowired
    private ForecastMapper forecastMapper;

    @PreAuthorize("hasRole('ADMINISTRATOR') Or hasRole('SUPERVISOR')")
    @GetMapping("/all")
    public ResponseEntity<ApiResult> getAllForecasts() {
        try {
            List<Forecast> forecasts = forecastService.getAllForecasts();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new WrappedEntity<>(forecastMapper.toDtoList(forecasts)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error(e.getMessage()));
        }
    }

    @PostMapping("/random")
    public ResponseEntity<ApiResult> updateForecastRandomly() {
        try {
            Forecast todayForecast = forecastService.getForecastFromToday();
            todayForecast.setRandomPrecipitation();
            forecastService.saveForecast(todayForecast);
            forecastEmulator.updateIrrigationScheduleBasedOnTodayForecast();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new WrappedEntity<>(forecastMapper.toDto(todayForecast)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error(e.getMessage()));
        }
    }
}
