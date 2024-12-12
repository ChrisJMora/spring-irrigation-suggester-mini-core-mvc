package com.example.demo.controller;

import com.example.demo.exception.EmptyRecordException;
import com.example.demo.exception.EmptyTableException;
import com.example.demo.exception.SaveRecordFailException;
import com.example.demo.model.agriculture.Forecast;
import com.example.demo.model.httpResponse.ApiResult;
import com.example.demo.model.httpResponse.Error;
import com.example.demo.model.httpResponse.WrappedEntity;
import com.example.demo.service.ForecastService;
import com.example.demo.tasks.ForecastEmulator;
import com.example.demo.utils.mapper.ForecastMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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
