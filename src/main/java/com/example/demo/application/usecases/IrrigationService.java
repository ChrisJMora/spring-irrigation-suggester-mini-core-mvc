package com.example.demo.application.usecases;

import com.example.demo.domain.models.Crop;
import com.example.demo.domain.models.DateRange;
import com.example.demo.domain.models.SortableElement;
import com.example.demo.application.ports.services.CropService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class IrrigationService {
    private final CropService cropService;
    @Qualifier("cropIrrigationsCountTopFilterStrategy")
    private final CropTopNFilter topNFilterStrategy;

    @Autowired
    public IrrigationService(CropService cropService, CropTopNFilter topNFilterStrategy) {
        this.cropService = cropService;
        this.topNFilterStrategy = topNFilterStrategy;
    }

    public SortableElement<?,?>[] getTopThreeCrops(DateRange dateRange) {
        List<Crop> crops = cropService.getAllCrops();
        return topNFilterStrategy.getTopN(crops, dateRange, 3);
    }
}
