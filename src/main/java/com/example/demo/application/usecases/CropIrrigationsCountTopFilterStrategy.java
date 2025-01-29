package com.example.demo.application.usecases;

import com.example.demo.application.dto.CropIrrigationsCount;
import com.example.demo.domain.models.Crop;
import com.example.demo.domain.models.DateRange;
import com.example.demo.domain.models.SortableElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CropIrrigationsCountTopFilterStrategy extends CropTopNFilter {

    private final IrrigationCalculatorService irrigationCalculator;

    @Autowired
    public CropIrrigationsCountTopFilterStrategy(IrrigationCalculatorService irrigationCalculator) {
        this.irrigationCalculator = irrigationCalculator;
    }

    @Override
    public SortableElement<?,?>[] getTopN(List<Crop> crops, DateRange dateRange, int topValue) {
        if (crops.isEmpty()) return new SortableElement[0];
        CropIrrigationsCount[] irrigationsCounts = irrigationCalculator.countIrrigationsForCrops(crops.toArray(new Crop[0]), dateRange);
        return Arrays.stream(irrigationsCounts)
                .sorted((count1, count2) -> Integer.compare(count2.getTimesIrrigated(), count1.getTimesIrrigated()))
                .limit(topValue)
                .toArray(SortableElement[]::new);
    }
}
