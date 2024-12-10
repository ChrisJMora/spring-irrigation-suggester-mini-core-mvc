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
    public void updateIrrigationScheduleBasedOnTodayForecast() {
        Forecast todayForecast = getOrCreateTodayForecast();
        Crop crop;
        try {
            crop = cropService.getCropByLocation(todayForecast.getLocation());
            irrigationScheduleManager.manageIrrigationScheduleForCrop(crop);
        } catch (EmptyRecordException ex) {
            log.warn("There are any crops that match the forecast location");
            irrigationScheduleManager.manageIrrigationScheduleForAllCrops();
        }
    }

    private Forecast createForecastForToday() {
        Forecast todayForecast = new Forecast(locationService.getRandomLocation());
        return forecastService.saveForecast(todayForecast);
    }

    public Forecast getOrCreateTodayForecast() {
        Forecast todayForecast;
        try {
            todayForecast = forecastService.getForecastFromToday();
        } catch (EmptyRecordException ex) {
            todayForecast = createForecastForToday();
        }
        return todayForecast;
    }
}
