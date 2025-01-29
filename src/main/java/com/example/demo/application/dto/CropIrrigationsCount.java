package com.example.demo.application.dto;

import com.example.demo.domain.models.SortableElement;

public class CropIrrigationsCount extends SortableElement<CropDTO, Integer> {

    public CropIrrigationsCount(CropDTO crop) {
        super(crop, 0);
    }

    public CropDTO getCrop() {
        return value;
    }

    public Integer getTimesIrrigated() {
        return sortingKey;
    }

    public void incrementIrrigationCount() {
        sortingKey++;
    }
}
