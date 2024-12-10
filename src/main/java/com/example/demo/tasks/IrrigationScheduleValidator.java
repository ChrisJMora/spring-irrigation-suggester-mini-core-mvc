package com.example.demo.tasks;

import com.example.demo.exception.EmptyFilterException;
import com.example.demo.model.agriculture.Crop;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IrrigationScheduleValidator {

    @Autowired
    private WaterCalculator waterCalculator;

    @Getter
    private float totalIrrigatedWater = 0;

    public boolean validateSchedulesIrrigation(Crop crop) {
        try {
            totalIrrigatedWater = waterCalculator.calculateTotalIrrigationFromSchedules(crop);
            return totalIrrigatedWater < crop.getWaterRequired();
        } catch (EmptyFilterException ex) {
            log.warn("No schedules for crop id: {}.", crop.getId());
            return false;
        }
    }

    public boolean validateSuggestedSchedulesIrrigation(Crop crop) {
        try {
            totalIrrigatedWater = waterCalculator.calculateTotalIrrigationFromSuggestedSchedules(crop);
            return totalIrrigatedWater < crop.getWaterRequired();
        } catch (EmptyFilterException ex) {
            log.warn("No pending suggested schedules for crop id: {}.", crop.getId());
            return false;
        }
    }
}