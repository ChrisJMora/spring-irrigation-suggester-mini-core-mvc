package com.example.demo.utils.mapper;

import com.example.demo.dto.ForecastDTO;
import com.example.demo.model.agriculture.Forecast;

import java.util.List;

public interface ForecastMapper {
    ForecastDTO toDto(Forecast forecast);
    Forecast toEntity(ForecastDTO forecastDTO);
    List<ForecastDTO> toDtoList(List<Forecast> forecasts);
    List<Forecast> toEntityList(List<ForecastDTO> forecastDTOs);
}
