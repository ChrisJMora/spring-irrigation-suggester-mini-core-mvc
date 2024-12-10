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
     * Get all forecasts from database, if the table is empty then throw an
     * exception.
     * @return List of the forecasts founded in the database.
     * @exception EmptyTableException When the table have not records.
     */
    @Override
    public List<Forecast> getAllForecasts() {
        List<Forecast> forecasts = forecastRepository.findAll();
        if (forecasts.isEmpty()) throw new EmptyTableException(Forecast.class);
        return forecasts;
    }

    /**
     * Get the forecast from today, if the record is empty then throw an
     * exception.
     * @return The forecast whose date coincides with today.
     * @exception EmptyRecordException When the record is empty.
     */
    @Override
    public Forecast getForecastFromToday() {
        LocalDate today = LocalDate.now();
        Optional<Forecast> todayForecast = forecastRepository.findByDate(today);
        if (todayForecast.isEmpty()) throw new EmptyRecordException(Forecast.class);
        return todayForecast.get();
    }

    /**
     * Create or update a forecast in the database, if the forecast is not
     * saved then throw an exception.
     * @param forecast The forecast that will be saved.
     * @exception SaveRecordFailException When the record couldn't been saved.
     */
    @Override
    public Forecast saveForecast(Forecast forecast) {
        Forecast savedForecast = forecastRepository.save(forecast);
        if (savedForecast.getId() == null) throw new SaveRecordFailException(Forecast.class);
        return savedForecast;
    }
}
