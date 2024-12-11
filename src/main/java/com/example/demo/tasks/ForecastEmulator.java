package com.example.demo.tasks;

import com.example.demo.exception.EmptyRecordException;
import com.example.demo.model.agriculture.Crop;
import com.example.demo.model.agriculture.Forecast;
import com.example.demo.service.CropService;
import com.example.demo.service.ForecastService;
import com.example.demo.service.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ForecastEmulator {

    @Autowired
    LocationService locationService;
    @Autowired
    ForecastService forecastService;
    @Autowired
    IrrigationScheduleManager irrigationScheduleManager;
    @Autowired
    CropService cropService;

     @Scheduled(cron = "0 0 0 * * ?") // 12:00 AM
//    @Scheduled(cron = "0 * * * * ?") // every minute
    public void updateIrrigationScheduleBasedOnTodayForecast() {
        log.info("Starting the update of irrigation schedule based on today's forecast.");
        Forecast todayForecast = getOrCreateTodayForecast();
        Crop crop;
        try {
            crop = cropService.getCropByLocation(todayForecast.getLocation());
            log.info("Retrieved crop for location {}: {}", todayForecast.getLocation(), crop);
            irrigationScheduleManager.manageIrrigationScheduleForCrop(crop);
        } catch (EmptyRecordException ex) {
            log.warn("There are no crops that match with forecast for today. Managing irrigation schedule for all crops.");
            irrigationScheduleManager.manageIrrigationScheduleForAllCrops();
        }
    }

    private Forecast createForecastForToday() {
        log.info("Creating a new forecast for today.");
        Forecast todayForecast = new Forecast(locationService.getRandomLocation());
        Forecast savedForecast = forecastService.saveForecast(todayForecast);
        log.info("New forecast created and saved: {}", savedForecast);
        return savedForecast;
    }

    public Forecast getOrCreateTodayForecast() {
        Forecast todayForecast;
        try {
            todayForecast = forecastService.getForecastFromToday();
            log.info("Retrieved today's forecast: {}", todayForecast);
        } catch (EmptyRecordException ex) {
            log.info("There is no forecast for today. Creating a new one.");
            todayForecast = createForecastForToday();
        }
        return todayForecast;
    }
}