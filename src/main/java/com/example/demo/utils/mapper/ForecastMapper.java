package com.example.demo.utils.mapper;

import com.example.demo.dto.ForecastDTO;
import com.example.demo.model.agriculture.Forecast;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ForecastMapper {
    ForecastDTO toDto(Forecast forecast);
    Forecast toEntity(ForecastDTO forecastDTO);
    List<ForecastDTO> toDtoList(List<Forecast> forecasts);
    List<Forecast> toEntityList(List<ForecastDTO> forecastDTOs);
}
