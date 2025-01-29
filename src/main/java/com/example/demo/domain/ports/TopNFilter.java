package com.example.demo.domain.ports;

import com.example.demo.domain.models.DateRange;
import com.example.demo.domain.models.SortableElement;

import java.util.List;

public interface TopNFilter<T> {
    SortableElement<?,?>[] getTopN(List<T> elements, DateRange dateRange, int topValue);
}
