package com.example.demo.utils.mapper;

import com.example.demo.dto.ForecastDTO;
import com.example.demo.model.agriculture.Forecast;
import com.example.demo.model.agriculture.Location;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-23T16:23:49-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
public class ForecastMapperImpl implements ForecastMapper {

    @Override
    public ForecastDTO toDto(Forecast forecast) {
        if ( forecast == null ) {
            return null;
        }

        ForecastDTO forecastDTO = new ForecastDTO();

        return forecastDTO;
    }

    @Override
    public Forecast toEntity(ForecastDTO forecastDTO) {
        if ( forecastDTO == null ) {
            return null;
        }

        Location location = null;

        Forecast forecast = new Forecast( location );

        return forecast;
    }

    @Override
    public List<ForecastDTO> toDtoList(List<Forecast> forecasts) {
        if ( forecasts == null ) {
            return null;
        }

        List<ForecastDTO> list = new ArrayList<ForecastDTO>( forecasts.size() );
        for ( Forecast forecast : forecasts ) {
            list.add( toDto( forecast ) );
        }

        return list;
    }

    @Override
    public List<Forecast> toEntityList(List<ForecastDTO> forecastDTOs) {
        if ( forecastDTOs == null ) {
            return null;
        }

        List<Forecast> list = new ArrayList<Forecast>( forecastDTOs.size() );
        for ( ForecastDTO forecastDTO : forecastDTOs ) {
            list.add( toEntity( forecastDTO ) );
        }

        return list;
    }
}
