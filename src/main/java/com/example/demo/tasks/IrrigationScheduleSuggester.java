package com.example.demo.tasks;

import com.example.demo.model.agriculture.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IrrigationScheduleSuggester {

    @Autowired
    private WaterCalculator waterCalculator;
    @Autowired
    private IrrigationScheduleService irrigationScheduleService;

    public void suggestIrrigationScheduleBasedOnConditions(Crop crop, Forecast todayForecast) {
        if (irrigationScheduleService.areCropAndForecastInSameLocation(crop,todayForecast)) {
            suggestIrrigationScheduleByForecast(crop, todayForecast);
        } else {
            suggestIrrigationScheduleByWaterRetention(crop);
        }
    }

    public void suggestIrrigationScheduleByWaterRetention(Crop crop) {
        suggestIrrigationSchedule(crop, 0);
    }

    public void suggestIrrigationScheduleByForecast(Crop crop, Forecast todayForecast) {
        Soil soil = crop.getSoil();
        float waterSuppliedByRain = waterCalculator.calculateRainWaterSupply(soil, todayForecast);
        suggestIrrigationSchedule(crop, waterSuppliedByRain);
    }

    private void suggestIrrigationSchedule(Crop crop, float additionalWater) {
        float waterRetained = waterCalculator.calculateWaterRetention(crop);
        if (waterRetained < 0) return;

        float totalWaterAvailable = waterRetained + additionalWater;
        if (totalWaterAvailable < crop.getWaterRequired()) {
            irrigationScheduleService.cancelPendingSuggestedSchedules(crop);
            float deficit = crop.getWaterRequired() - totalWaterAvailable;
            irrigationScheduleService.createIrrigationSchedule(crop, deficit);
        }
    }
}
