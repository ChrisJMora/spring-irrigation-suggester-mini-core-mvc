package com.example.demo.tasks;

import com.example.demo.exception.EmptyFilterException;
import com.example.demo.exception.SaveRecordFailException;
import com.example.demo.model.agriculture.Crop;
import com.example.demo.model.agriculture.Forecast;
import com.example.demo.model.agriculture.SuggestedSchedule;
import com.example.demo.model.agriculture.SuggestedScheduleStatus;
import com.example.demo.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@Service
public class IrrigationScheduleService {

    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private WaterCalculator waterCalculator;

    public void createIrrigationSchedule(Crop crop, float waterRequired) {
        try {
            LocalTime irrigationDuration = waterCalculator.calculateIrrigationDuration(waterRequired);
            SuggestedSchedule schedule = new SuggestedSchedule(irrigationDuration, crop);
            scheduleService.saveSuggestedSchedule(schedule);
            log.info("Irrigation schedule created for crop: {} with duration: {}", crop.getId(), irrigationDuration);
        } catch (SaveRecordFailException ex) {
            log.error("Failed to save the suggested irrigation schedule for crop: {}. Error: {}", crop.getId(), ex.getMessage());
        }
    }

    public List<SuggestedSchedule> fetchPendingSuggestedSchedules() {
        return scheduleService.getAllSuggestedScheduleByStatus(SuggestedScheduleStatus.PENDING);
    }

    public List<SuggestedSchedule> fetchPendingSuggestedSchedules(Crop crop) {
        return scheduleService.getAllSuggestedScheduleByStatusAndCrop(SuggestedScheduleStatus.PENDING, crop);
    }

    public void cancelPendingSuggestedSchedules(Crop crop) {
        try {
            List<SuggestedSchedule> schedules = fetchPendingSuggestedSchedules(crop);
            for (SuggestedSchedule schedule : schedules) {
                cancelSuggestedSchedule(schedule);
            }
        } catch (EmptyFilterException ex) {
            log.info("No pending suggested schedules found for crop id: {}", crop.getId());
        }
    }

    public void cancelSuggestedSchedule(SuggestedSchedule schedule) {
        schedule.setStatus(SuggestedScheduleStatus.CANCELED);
        scheduleService.saveSuggestedSchedule(schedule);
    }

    public boolean isSuggestedScheduleOutdated(SuggestedSchedule schedule) {
        LocalDate today = LocalDate.now();
        return !schedule.getCreatedAt().toLocalDate().equals(today);
    }

    public boolean areCropAndForecastInSameLocation(Crop crop, Forecast forecast) {
        return crop.getLocation().equals(forecast.getLocation());
    }
}
