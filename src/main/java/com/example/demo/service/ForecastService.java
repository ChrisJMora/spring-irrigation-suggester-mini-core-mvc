package com.example.demo.service;

import com.example.demo.exception.EmptyRecordException;
import com.example.demo.exception.EmptyTableException;
import com.example.demo.exception.SaveRecordFailException;
import com.example.demo.model.agriculture.Forecast;

import java.util.List;

public interface ForecastService {
    /**
     * Get all forecasts from database, if the table is empty then throw an
     * exception.
     * @return List of the forecasts founded in the database.
     * @exception EmptyTableException When the table have not records.
     */
    List<Forecast> getAllForecasts();

    /**
     * Get the forecast from today, if the record is empty then throw an
     * exception.
     * @return The forecast whose date coincides with today.
     * @exception EmptyRecordException When the record is empty.
     */
    Forecast getForecastFromToday();

    /**
     * Create or update a forecast in the database, if the forecast is not
     * saved then throw an exception.
     * @param forecast The forecast that will be saved.
     * @exception SaveRecordFailException When the record couldn't been saved.
     */
    void saveForecast(Forecast forecast);
}
