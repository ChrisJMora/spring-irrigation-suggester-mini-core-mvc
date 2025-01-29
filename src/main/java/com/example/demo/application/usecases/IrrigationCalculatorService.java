package com.example.demo.application.usecases;

import com.example.demo.application.dto.CropIrrigationsCount;
import com.example.demo.domain.models.DateRange;
import com.example.demo.domain.models.Crop;
import com.example.demo.domain.models.Schedule;
import com.example.demo.application.ports.services.ScheduleService;
import com.example.demo.application.ports.mappers.CropMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IrrigationCalculatorService {

    private final ScheduleService scheduleService;
    private final CropMapper cropMapper;

    @Autowired
    public IrrigationCalculatorService(ScheduleService scheduleService, CropMapper cropMapper) {
        this.scheduleService = scheduleService;
        this.cropMapper = cropMapper;
    }

    public CropIrrigationsCount countIrrigationsForCrop(Crop crop, DateRange dateRange) {
        List<Schedule> schedules = scheduleService.getAllSchedulesByCrop(crop);
        return calculateIrrigations(crop, schedules, dateRange);
    }

    public CropIrrigationsCount[] countIrrigationsForCrops(Crop[] crops, DateRange dateRange) {
        CropIrrigationsCount[] irrigationsCount = new CropIrrigationsCount[crops.length];
        for (int i = 0; i < crops.length; i++) {
            irrigationsCount[i] = countIrrigationsForCrop(crops[i], dateRange);
        }
        return irrigationsCount;
    }

    private CropIrrigationsCount calculateIrrigations(Crop crop, List<Schedule> schedules, DateRange dateRange) {
        CropIrrigationsCount irrigationsCount = new CropIrrigationsCount(cropMapper.toDTO(crop));
        for (Schedule schedule : schedules) {
            if (dateRange.isDateWithinRange(schedule.getDate())) {
                irrigationsCount.incrementIrrigationCount();
            }
        }
        return irrigationsCount;
    }
}
