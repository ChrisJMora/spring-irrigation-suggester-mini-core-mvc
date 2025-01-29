package com.example.demo.application.usecases;

import com.example.demo.domain.models.Crop;
import com.example.demo.domain.models.DateRange;
import com.example.demo.domain.models.SortableElement;
import com.example.demo.domain.ports.TopNFilter;

import java.util.List;

public abstract class CropTopNFilter implements TopNFilter<Crop> {
    public abstract SortableElement<?,?>[] getTopN(List<Crop> crops, DateRange dateRange, int topValue);
}
