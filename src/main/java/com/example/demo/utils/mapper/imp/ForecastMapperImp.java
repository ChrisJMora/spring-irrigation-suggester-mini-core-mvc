package com.example.demo.utils.mapper.imp;

import com.example.demo.dto.ForecastDTO;
import com.example.demo.model.agriculture.Forecast;
import com.example.demo.utils.mapper.ForecastMapper;
import com.example.demo.utils.mapper.LocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ForecastMapperImp implements ForecastMapper {

    @Autowired
    private LocationMapper locationMapper;

    @Override
    public ForecastDTO toDto(Forecast forecast) {
        if (forecast == null) return null;

        ForecastDTO forecastDTO = new ForecastDTO();
        forecastDTO.setProbability(forecast.getProbability());
        forecastDTO.setDate(forecast.getDate());
        forecastDTO.setLocation(locationMapper.toDto(forecast.getLocation()));
        forecastDTO.setPrecipitation(forecast.getPrecipitation());

        return forecastDTO;
    }

    @Override
    public Forecast toEntity(ForecastDTO forecastDTO) {
        if (forecastDTO == null) return null;

        Forecast forecast = new Forecast();
        forecast.setProbability(forecastDTO.getProbability());
        forecast.setDate(forecastDTO.getDate());
        forecast.setLocation(locationMapper.toEntity(forecastDTO.getLocation()));
        forecast.setPrecipitation(forecastDTO.getPrecipitation());

        return forecast;
    }

    @Override
    public List<ForecastDTO> toDtoList(List<Forecast> forecasts) {
        if (forecasts == null) return null;

        List<ForecastDTO> list = new ArrayList<>();
        for (Forecast forecast : forecasts) {
            list.add( toDto(forecast) );
        }
        return list;
    }

    @Override
    public List<Forecast> toEntityList(List<ForecastDTO> forecastDTOs) {
        if (forecastDTOs == null) return null;

        List<Forecast> list = new ArrayList<>();
        for (ForecastDTO forecastDTO : forecastDTOs) {
            list.add( toEntity(forecastDTO) );
        }
        return list;
    }
}
