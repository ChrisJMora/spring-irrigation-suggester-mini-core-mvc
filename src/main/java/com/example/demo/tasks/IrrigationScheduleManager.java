package com.example.demo.tasks;

import com.example.demo.exception.EmptyFilterException;
import com.example.demo.exception.EmptyTableException;
import com.example.demo.exception.SaveRecordFailException;
import com.example.demo.model.agriculture.Crop;
import com.example.demo.model.agriculture.Forecast;
import com.example.demo.model.agriculture.SuggestedSchedule;
import com.example.demo.service.CropService;
import com.example.demo.service.ForecastService;
import com.example.demo.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class IrrigationScheduleManager {

    @Autowired
    private CropService cropService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private IrrigationScheduleService irrigationScheduleService;
    @Autowired
    private IrrigationScheduleValidator irrigationScheduleValidator;
    @Autowired
    private IrrigationScheduleSuggester irrigationScheduleSuggester;
    @Autowired
    private ForecastService forecastService;

    @Scheduled(cron = "0 0 0 * * ?") // 12:00 AM
    public void cancelOutdatedSuggestedSchedules() {
        List<SuggestedSchedule> suggestedPendingSchedules;
        try {
            suggestedPendingSchedules = scheduleService.fetchPendingSuggestedSchedules();
            for (SuggestedSchedule schedule : suggestedPendingSchedules) {
                if (scheduleService.isSuggestedScheduleOutdated(schedule)) {
                    scheduleService.cancelSuggestedSchedule(schedule);
                }
            }
        } catch (EmptyFilterException ex) {
            log.info("There are no pending suggested schedules yet.", ex);
        } catch (SaveRecordFailException ex) {
            log.error("A problem occur updating a suggested schedule.", ex);
        }
    }

    @Scheduled(cron = "0 0 0 * * ?") // 12:00 AM
    public void manageIrrigationScheduleForAllCrops() {
        try {
            for (Crop crop : cropService.getAllCrops()) {
                manageIrrigationScheduleForCrop(crop);
            }
        } catch (EmptyTableException ex) {
            log.error("There are no crops for which to suggest an irrigation schedule", ex);
        }
    }

    public void manageIrrigationScheduleForCrop(Crop crop) {
        log.info("Starting irrigation management for crop id: {}", crop.getId());
        if (irrigationScheduleValidator.validateSchedulesIrrigation(crop)) {
            log.info("All schedules for crop id: {} met its water needs", crop.getId());
            irrigationScheduleService.cancelPendingSuggestedSchedules(crop);
            return;
        }
        if (irrigationScheduleValidator.validateSuggestedSchedulesIrrigation(crop)) {
            log.info("The suggested irrigation schedule for crop id: {} meets its water needs.", crop.getId());
        }
        Forecast todayForecast = forecastService.getOrCreateTodayForecast();
        irrigationScheduleSuggester.suggestIrrigationScheduleBasedOnConditions(crop, todayForecast);
    }
}