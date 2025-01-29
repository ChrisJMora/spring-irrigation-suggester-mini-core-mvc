package com.example.demo.application.ports.mappers;

import com.example.demo.application.dto.ForecastDTO;
import com.example.demo.domain.models.Forecast;

import java.util.List;

public interface ForecastMapper {
    ForecastDTO toDto(Forecast forecast);
    Forecast toEntity(ForecastDTO forecastDTO);
    List<ForecastDTO> toDtoList(List<Forecast> forecasts);
    List<Forecast> toEntityList(List<ForecastDTO> forecastDTOs);
}
