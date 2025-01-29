package com.example.demo.application.usecases;

import com.example.demo.domain.exceptions.EmptyFilterException;
import com.example.demo.domain.models.Crop;
import com.example.demo.domain.models.SuggestedSchedule;
import com.example.demo.application.ports.services.ScheduleService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class IrrigationScheduleValidator {

    @Autowired
    private WaterCalculator waterCalculator;
    @Autowired
    private ScheduleService scheduleService;

    @Getter
    private float totalIrrigatedWater = 0;

    public boolean validateSchedulesIrrigation(Crop crop) {
        try {
            totalIrrigatedWater = waterCalculator.calculateTotalIrrigationFromSchedules(crop);
            return totalIrrigatedWater > crop.getWaterRequired();
        } catch (EmptyFilterException ex) {
            log.warn("No schedules for crop id: {}.", crop.getId());
            return false;
        }
    }

    public boolean validateSuggestedSchedulesIrrigation(Crop crop) {
        try {
            totalIrrigatedWater = waterCalculator.calculateTotalIrrigationFromSuggestedSchedules(crop);
            return totalIrrigatedWater > crop.getWaterRequired();
        } catch (EmptyFilterException ex) {
            log.warn("No pending suggested schedules for crop id: {}.", crop.getId());
            return false;
        }
    }

    public boolean validateSuggestedScheduleIrrigation(SuggestedSchedule schedule) {
        try {
            Crop crop = schedule.getCrop();
            List<SuggestedSchedule> pendingSchedules =
                    scheduleService.fetchPendingSuggestedSchedules(crop);
            for (SuggestedSchedule pendingSchedule : pendingSchedules) {
                if (schedule.getDelayTime().equals(pendingSchedule.getDelayTime()))
                    return false;
            }
            return true;
        } catch (EmptyFilterException ex) {
            return true;
        }
    }
}