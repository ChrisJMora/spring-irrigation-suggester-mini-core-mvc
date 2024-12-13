package com.example.demo.tasks;

import com.example.demo.exception.EmptyFilterException;
import com.example.demo.exception.EmptyRecordException;
import com.example.demo.model.agriculture.*;
import com.example.demo.service.ScheduleService;
import com.example.demo.service.SensorService;
import com.example.demo.service.SprinklerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@Service
public class WaterCalculator {

    @Autowired
    private SensorService sensorService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private SprinklerService sprinklerService;

    public float calculateWaterRetention(Crop crop) {
        Soil soil = crop.getSoil();
        try {
            List<Sensor> sensors = sensorService.getAllSensorsByCrop(crop);
            // Calculate the total retained water in the crop
            float waterRetention = (calculateAverageHumidity(sensors) / 100) *
                    (soil.getWaterRetention() / 100) *
                    crop.getRootHeight() * 1000;
            log.info("Calculated water retention for crop id {}: {} liters", crop.getId(), waterRetention);
            return waterRetention;
        } catch (EmptyFilterException ex) {
            log.error("Crop with id {} has no associated sensors.", crop.getId(), ex);
            return 0; // Indicate that the calculation could not be performed
        }
    }

    public float calculateIrrigatedWaterVolume(LocalTime irrigationDuration) {
        // Convert the irrigation time to seconds
        long seconds = irrigationDuration.toSecondOfDay();
        float totalFlowRate = 0;
        // Retrieve all sprinklers and calculate the total flow rate
        List<Sprinkler> sprinklers = sprinklerService.getAllSprinklers();
        for (Sprinkler sprinkler : sprinklers) {
            totalFlowRate += sprinkler.getFlowRate();
        }
        // Check if the total flow rate is valid
        if (totalFlowRate <= 0) {
            log.error("Total flow rate of sprinklers is zero or negative. Cannot calculate irrigated water volume.");
            throw new IllegalArgumentException("The total flow rate of the sprinklers cannot be zero or negative.");
        }
        // Calculate and return the total volume of water irrigated
        float irrigatedWaterVolume = totalFlowRate * seconds;
        log.info("Calculated irrigated water volume: {} liters over {} seconds with total flow rate: {} liters/second",
                irrigatedWaterVolume, seconds, totalFlowRate);
        return irrigatedWaterVolume;
    }

    public float calculateRainWaterSupply(Soil soil, Forecast forecast) {
        float rainWaterSupply = soil.getWaterRetention() * forecast.getPrecipitation();
        log.info("Calculated rain water supply: {} liters", rainWaterSupply);
        return rainWaterSupply;
    }

    private float calculateAverageHumidity(List<Sensor> sensors) {
        try {
            float totalHumidity = 0;
            for (Sensor sensor : sensors) {
                // Get the most recent record from each sensor
                SensorRecord recentRecord = sensorService.getMostRecentRecord(sensor);
                totalHumidity += recentRecord.getHumidity();
            }
            // Calculate the average humidity
            float averageHumidity = totalHumidity / sensors.size();
            log.info("Calculated average humidity from {} sensors: {}%", sensors.size(), averageHumidity);
            return averageHumidity;
        } catch (EmptyRecordException ex) {
            return 0;
        }
    }

    public float calculateTotalIrrigationFromSchedules(Crop crop) {
        try {
            // Fetch all pending schedules for the given crop
            List<Schedule> schedules = scheduleService.getAllScheduleByCropAndStatusAndDate(crop, ScheduleStatus.PENDING, LocalDate.now());
            float totalIrrigatedWater = 0;
            for (Schedule schedule : schedules) {
                LocalTime irrigationDuration = calculateIrrigationDuration(schedule.getStartTime(), schedule.getEndTime());
                totalIrrigatedWater += calculateIrrigatedWaterVolume(irrigationDuration);
            }
            return totalIrrigatedWater;
        } catch (EmptyFilterException ex) {
            return 0;
        }
    }

    public float calculateTotalIrrigationFromSuggestedSchedules(Crop crop) {
        // Fetch all pending suggested schedules for the given crop
        List<SuggestedSchedule> schedules = scheduleService.getAllSuggestedScheduleByStatusAndCrop(SuggestedScheduleStatus.PENDING, crop);
        float totalIrrigatedWater = 0;
        // Handle case where there is exactly one pending schedule
        for (SuggestedSchedule schedule : schedules) {
            totalIrrigatedWater += calculateIrrigatedWaterVolume(schedule.getDelayTime());
        }
        return totalIrrigatedWater;
    }

    public LocalTime calculateIrrigationDuration(LocalTime start,
                                                LocalTime end) {
        Duration duration = Duration.between(start, end);
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;
        return LocalTime.of((int) hours, (int) minutes, (int) seconds);
    }

    public LocalTime calculateIrrigationDuration(float waterRequired) {
        float totalFlowRate = 0; // Total flow rate from all sprinklers
        List<Sprinkler> sprinklers = sprinklerService.getAllSprinklers();
        // Calculate the total flow rate from all sprinklers
        for (Sprinkler sprinkler : sprinklers) {
            totalFlowRate += sprinkler.getFlowRate();
        }
        // Calculate the total time needed for irrigation in seconds
        long seconds = Math.round(waterRequired / totalFlowRate);
        // Return the total time of irrigation as a LocalTime object
        return LocalTime.ofSecondOfDay(seconds);
    }
}