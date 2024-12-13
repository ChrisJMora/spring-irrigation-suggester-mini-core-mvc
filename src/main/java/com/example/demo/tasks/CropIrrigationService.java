package com.example.demo.tasks;

import com.example.demo.dto.CropDTO;
import com.example.demo.dto.CropWaterIrrigated;
import com.example.demo.model.agriculture.Crop;
import com.example.demo.model.agriculture.Schedule;
import com.example.demo.service.CropService;
import com.example.demo.service.ScheduleService;
import com.example.demo.utils.mapper.CropMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CropIrrigationService {
    @Autowired
    private CropService cropService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private WaterCalculator waterCalculator;
    @Autowired
    private CropMapper cropMapper;

    public List<CropWaterIrrigated> getTopThreeIrrigatedCrops(LocalDate startDate, LocalDate endDate) {
        List<Crop> crops = cropService.getAllCrops();
        Map<Crop, Double> waterUsageMap = new HashMap<>();

        for (Crop crop : crops) {
            double totalWaterUsed = 0;
            List<Schedule> schedules = scheduleService.getAllScheduleByCrop(crop);
            for (Schedule schedule : schedules) {
                if (!schedule.getDate().isBefore(startDate) && !schedule.getDate().isAfter(endDate)) {
                    LocalTime irrigationDuration = waterCalculator.calculateIrrigationDuration(schedule.getStartTime(), schedule.getEndTime());
                    totalWaterUsed += waterCalculator.calculateIrrigatedWaterVolume(irrigationDuration);
                }
            }
            if (totalWaterUsed > 0) {
                waterUsageMap.put(crop, totalWaterUsed);
            }
        }

        List<CropWaterIrrigated> topThreeCrops = waterUsageMap.entrySet()
                .stream()
                .sorted(Map.Entry.<Crop, Double>comparingByValue().reversed())
                .limit(3)
                .map(entry -> new CropWaterIrrigated(cropMapper.toDTO(entry.getKey()), entry.getValue()))
                .collect(Collectors.toList());

        // Log de los tres mejores cultivos
        log.info("Top 3 cultivos más regados:");
        for (CropWaterIrrigated cropWaterIrrigated : topThreeCrops) {
            log.info("Cultivo: {}, Agua utilizada: {} l/m2", cropWaterIrrigated.getCrop().getName(), cropWaterIrrigated.getTotalWaterIrrigated());
        }

        return topThreeCrops;
    }
}
