/**
 * Implementation service for managing weather forecasts.
 * This service provides methods to perform CRUD operations on forecasts in the database.
 */
package com.example.demo.service;

import com.example.demo.exception.EmptyRecordException;
import com.example.demo.exception.EmptyTableException;
import com.example.demo.exception.SaveRecordFailException;
import com.example.demo.model.agriculture.Forecast;

import java.util.List;

public interface ForecastService {
    /**
     * Retrieves all forecasts from the database.
     * If the table is empty, an exception is thrown.
     *
     * @return A list of forecasts found in the database.
     * @throws EmptyTableException When the forecasts table has no records.
     */
    List<Forecast> getAllForecasts();

    /**
     * Retrieves the forecast for today.
     * If there is no forecast for today, an exception is thrown.
     *
     * @return The forecast for today's date.
     * @throws EmptyRecordException When no forecast is found for today.
     */
    Forecast getForecastFromToday();

    /**
     * Creates or updates a forecast in the database.
     * If the forecast is not saved successfully, an exception is thrown.
     *
     * @param forecast The forecast to be saved.
     * @return The saved forecast.
     * @throws SaveRecordFailException When the forecast could not be saved.
     */
    Forecast saveForecast(Forecast forecast);
}
