package com.example.demo.tasks;

import com.example.demo.exception.EmptyFilterException;
import com.example.demo.model.agriculture.Crop;
import com.example.demo.model.agriculture.SuggestedSchedule;
import com.example.demo.service.ScheduleService;
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