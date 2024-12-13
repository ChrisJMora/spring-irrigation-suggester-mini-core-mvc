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
        Map<Crop, Integer> irrigationCountMap = new HashMap<>();

        for (Crop crop : crops) {
            int timesIrrigated = 0;
            List<Schedule> schedules = scheduleService.getAllScheduleByCrop(crop);
            for (Schedule schedule : schedules) {
                if (!schedule.getDate().isBefore(startDate) && !schedule.getDate().isAfter(endDate)) {
                    timesIrrigated += 1;
                }
            }
            if (timesIrrigated > 0) {
                irrigationCountMap.put(crop, timesIrrigated);
            }
        }

        List<CropWaterIrrigated> topThreeCrops = irrigationCountMap.entrySet()
                .stream()
                .sorted(Map.Entry.<Crop, Integer>comparingByValue().reversed())
                .limit(3)
                .map(entry -> new CropWaterIrrigated(cropMapper.toDTO(entry.getKey()), entry.getValue()))
                .collect(Collectors.toList());

        // Log de los tres mejores cultivos
        log.info("Top 3 cultivos más regados:");
        for (CropWaterIrrigated cropWaterIrrigated : topThreeCrops) {
            log.info("Cultivo: {}, Número de riegos: {}", cropWaterIrrigated.getCrop().getName(), cropWaterIrrigated.getTotalWaterIrrigated());
        }

        return topThreeCrops;
    }
}
