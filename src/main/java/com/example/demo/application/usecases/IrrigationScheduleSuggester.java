package com.example.demo.application.usecases;

import com.example.demo.domain.models.Crop;
import com.example.demo.domain.models.Forecast;
import com.example.demo.domain.models.Soil;
import com.example.demo.application.ports.services.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IrrigationScheduleSuggester {

    @Autowired
    private WaterCalculator waterCalculator;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private IrrigationScheduleService irrigationScheduleService;

    public void suggestIrrigationScheduleBasedOnConditions(Crop crop, Forecast todayForecast) {
        if (scheduleService.areCropAndForecastInSameLocation(crop, todayForecast)) {
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
        float waterFromSchedules = waterCalculator.calculateTotalIrrigationFromSchedules(crop);
        float waterRetained = waterCalculator.calculateWaterRetention(crop);
        float totalWaterIrrigated = waterFromSchedules + waterRetained + additionalWater;
        if (totalWaterIrrigated < crop.getWaterRequired()) {
            float deficit = crop.getWaterRequired() - totalWaterIrrigated;
            log.info("Crop id: {} requires {} units of water, currently irrigated: {} units, water deficit: {} units.",
                    crop.getId(), crop.getWaterRequired(), totalWaterIrrigated, deficit);
            irrigationScheduleService.createIrrigationSchedule(crop, deficit);
        }
    }
}
