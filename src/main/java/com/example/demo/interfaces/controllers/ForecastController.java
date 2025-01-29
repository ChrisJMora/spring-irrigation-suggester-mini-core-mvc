package com.example.demo.interfaces.controllers;

import com.example.demo.domain.exceptions.EmptyRecordException;
import com.example.demo.domain.exceptions.EmptyTableException;
import com.example.demo.domain.exceptions.SaveRecordFailException;
import com.example.demo.domain.models.Forecast;
import com.example.demo.domain.models.ApiResult;
import com.example.demo.domain.models.Error;
import com.example.demo.domain.models.WrappedEntity;
import com.example.demo.application.ports.services.ForecastService;
import com.example.demo.application.usecases.ForecastEmulator;
import com.example.demo.application.ports.mappers.ForecastMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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
        } catch (EmptyTableException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Error(ex.getMessage()));
        } catch (Exception ex) {
            log.error("An error occurred while updating the crop: ", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error(" An unexpected error occurred."));
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
        } catch (EmptyRecordException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Error(ex.getMessage()));
        } catch (SaveRecordFailException ex) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(new Error(ex.getMessage()));
        } catch (Exception ex) {
            log.error("An error occurred while updating the crop: ", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error(" An unexpected error occurred."));
        }
    }
}
