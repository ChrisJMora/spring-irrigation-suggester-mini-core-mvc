package com.example.demo.application.usecases;

import com.example.demo.domain.exceptions.EmptyFilterException;
import com.example.demo.domain.exceptions.SaveRecordFailException;
import com.example.demo.domain.models.Crop;
import com.example.demo.domain.models.SuggestedSchedule;
import com.example.demo.application.ports.services.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Slf4j
@Service
public class IrrigationScheduleService {

    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private WaterCalculator waterCalculator;
    @Autowired
    private IrrigationScheduleValidator irrigationScheduleValidator;

    public void createIrrigationSchedule(Crop crop, float waterRequired) {
        try {
            LocalTime irrigationDuration = waterCalculator.calculateIrrigationDuration(waterRequired);
            if (isTimeZero(irrigationDuration)) {
                log.info("The irrigation duration is 00:00:00, therefore no irrigation schedule is created");
                return;
            }
            SuggestedSchedule schedule = new SuggestedSchedule(irrigationDuration, crop);
            if (irrigationScheduleValidator.validateSuggestedScheduleIrrigation(schedule)) {
                cancelPendingSuggestedSchedules(crop);
                scheduleService.saveSuggestedSchedule(schedule);
                log.info("Irrigation schedule created for crop: {} with duration: {}", crop.getId(), irrigationDuration);
            } else {
                log.info("There is already an irrigation schedule with the same duration");
            }
        } catch (SaveRecordFailException ex) {
            log.error("Failed to save the suggested irrigation schedule for crop: {}. Error: {}", crop.getId(), ex.getMessage());
        }
    }

    public void cancelPendingSuggestedSchedules(Crop crop) {
        try {
            List<SuggestedSchedule> schedules =
                scheduleService.fetchPendingSuggestedSchedules(crop);
            for (SuggestedSchedule schedule : schedules) {
                scheduleService.cancelSuggestedSchedule(schedule);
            }
        } catch (EmptyFilterException ex) {
            log.info("No pending suggested schedules found for crop id: {}", crop.getId());
        }
    }

    public static boolean isTimeZero(LocalTime time) {
        return time.getHour() == 0 && time.getMinute() == 0 && time.getSecond() == 0;
    }
}
