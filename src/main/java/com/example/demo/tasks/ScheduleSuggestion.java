package com.example.demo.tasks;

import com.example.demo.model.agriculture.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
public class ScheduleSuggestion {

    @Autowired
    private ForecastService forecastService;
    @Autowired
    private CropService cropService;
    @Autowired
    private SuggestedScheduleService suggestedScheduleService;
    @Autowired
    private SensorService sensorService;
    @Autowired
    private SprinklerService sprinklerService;

    @Scheduled(cron = "0 0/1 * * * ?") // Every minute
    public void ProposeSchedule() {
        try {
            Forecast todayForecast = forecastService.getForecastFromToday();
            List<Crop> crops = cropService.getAllCrops();
            for(Crop crop : crops) {
                // ---------- CASE 1 ----------
                Soil soil = crop.getSoil();
                // Get the total retained water in the crops
                List<Sensor> sensors = crop.getSensors();
                float waterRetained = getHumidityPercentageAverage(sensors)/100 * soil.getWaterRetention()/100 * crop.getRootHeight() * 1000;
                // If the retained water is not sufficient then try with forecast
                if (waterRetained > crop.getWaterRequired()) continue;
                // ---------- CASE 2 ----------
                // The location of the crop and the forecast must coincide
                if (!crop.getLocation().equals(todayForecast.getLocation())) continue;
                // Get the supplied water by the rain
                float waterSuppliedByRain = soil.getWaterRetention() * todayForecast.getPrecipitation();
                // If the supplied water by the rain is not sufficient then make an irrigation schedule
                if (waterSuppliedByRain > crop.getWaterRequired()) continue;
                // ---------- CASE 3 ----------
                float deficit = crop.getWaterRequired() - waterSuppliedByRain - waterRetained;
                // Calculate the total time needed by the sprinklers to fill the deficit
                LocalTime irrigationTime = calculateIrrigationTime(deficit);
                SuggestedSchedule schedule = new SuggestedSchedule(irrigationTime, crop);
                suggestedScheduleService.saveSuggestedSchedule(schedule);
            }
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
    }

    private float getHumidityPercentageAverage(List<Sensor> sensors) {
        float average = 0;
        for (Sensor sensor : sensors) {
            // Get the recent record form each sensor
            SensorRecord recentRecord = sensorService.getMostRecentRecord(sensor);
            average += recentRecord.getHumidity();
        }
        // Calculate the humidity average
        return average / sensors.size();
    }

    public LocalTime calculateIrrigationTime(float waterRequired) {
        float totalCaudal = 0;
        List<Sprinkler> sprinklers = sprinklerService.getAllSprinklers();
        for (Sprinkler sprinkler : sprinklers) {
            // Calculate the total caudal from all sprinklers
            totalCaudal += sprinkler.getCaudal();
        }
        long seconds = Math.round(waterRequired / totalCaudal);
        // Return the total time of irrigation
        return LocalTime.ofSecondOfDay(seconds);
    }
}
