/**
 * Implementation service for managing weather forecasts.
 * This service provides methods to perform CRUD operations on forecasts in the database.
 */
package com.example.demo.service.imp;

import com.example.demo.exception.EmptyRecordException;
import com.example.demo.exception.EmptyTableException;
import com.example.demo.exception.SaveRecordFailException;
import com.example.demo.model.agriculture.Forecast;
import com.example.demo.persistence.ForecastRepository;
import com.example.demo.service.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ForecastServiceImp implements ForecastService {

    @Autowired
    private ForecastRepository forecastRepository;

    /**
     * Retrieves all forecasts from the database.
     * If the table is empty, an exception is thrown.
     *
     * @return A list of forecasts found in the database.
     * @throws EmptyTableException When the forecasts table has no records.
     */
    @Override
    public List<Forecast> getAllForecasts() {
        List<Forecast> forecasts = forecastRepository.findAll();
        if (forecasts.isEmpty()) throw new EmptyTableException(Forecast.class);
        return forecasts;
    }

    /**
     * Retrieves the forecast for today.
     * If there is no forecast for today, an exception is thrown.
     *
     * @return The forecast for today's date.
     * @throws EmptyRecordException When no forecast is found for today.
     */
    @Override
    public Forecast getForecastFromToday() {
        LocalDate today = LocalDate.now();
        Optional<Forecast> todayForecast = forecastRepository.findByDate(today);
        if (todayForecast.isEmpty()) throw new EmptyRecordException(Forecast.class);
        return todayForecast.get();
    }

    /**
     * Creates or updates a forecast in the database.
     * If the forecast is not saved successfully, an exception is thrown.
     *
     * @param forecast The forecast to be saved.
     * @return The saved forecast.
     * @throws SaveRecordFailException When the forecast could not be saved.
     */
    @Override
    public Forecast saveForecast(Forecast forecast) {
        Forecast savedForecast = forecastRepository.save(forecast);
        if (savedForecast.getId() == null) throw new SaveRecordFailException(Forecast.class);
        return savedForecast;
    }
}