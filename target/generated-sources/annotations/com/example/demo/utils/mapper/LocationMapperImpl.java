package com.example.demo.utils.mapper;

import com.example.demo.dto.LocationDTO;
import com.example.demo.model.agriculture.Location;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-25T13:31:05-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
public class LocationMapperImpl implements LocationMapper {

    @Override
    public LocationDTO toDto(Location location) {
        if ( location == null ) {
            return null;
        }

        LocationDTO locationDTO = new LocationDTO();

        return locationDTO;
    }

    @Override
    public Location toEntity(LocationDTO locationDTO) {
        if ( locationDTO == null ) {
            return null;
        }

        Location location = new Location();

        return location;
    }

    @Override
    public List<LocationDTO> toDtoList(List<Location> locations) {
        if ( locations == null ) {
            return null;
        }

        List<LocationDTO> list = new ArrayList<LocationDTO>( locations.size() );
        for ( Location location : locations ) {
            list.add( toDto( location ) );
        }

        return list;
    }

    @Override
    public List<Location> toEntityList(List<LocationDTO> locationDTOs) {
        if ( locationDTOs == null ) {
            return null;
        }

        List<Location> list = new ArrayList<Location>( locationDTOs.size() );
        for ( LocationDTO locationDTO : locationDTOs ) {
            list.add( toEntity( locationDTO ) );
        }

        return list;
    }
}
