package com.example.demo.utils.mapper;

import com.example.demo.dto.ForecastDTO;
import com.example.demo.model.agriculture.Forecast;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {LocationMapper.class})
public interface ForecastMapper {
    ForecastMapper INSTANCE = Mappers.getMapper(ForecastMapper.class);

    ForecastDTO toDto(Forecast forecast);
    Forecast toEntity(ForecastDTO forecastDTO);
    List<ForecastDTO> toDtoList(List<Forecast> forecasts);
    List<Forecast> toEntityList(List<ForecastDTO> forecastDTOs);
}
