package com.example.demo.application.usecases;

import com.example.demo.domain.exceptions.EmptyRecordException;
import com.example.demo.domain.models.Crop;
import com.example.demo.domain.models.Forecast;
import com.example.demo.application.ports.services.CropService;
import com.example.demo.application.ports.services.ForecastService;
import com.example.demo.application.ports.services.LocationService;
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
        log.info("Starting the update of irrigation schedule based on today's forecast.");
        Forecast todayForecast = forecastService.getOrCreateTodayForecast();
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
}