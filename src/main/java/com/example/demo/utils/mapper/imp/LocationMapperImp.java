package com.example.demo.utils.mapper.imp;

import com.example.demo.dto.LocationDTO;
import com.example.demo.model.agriculture.Location;
import com.example.demo.utils.mapper.LocationMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationMapperImp implements LocationMapper {
    @Override
    public LocationDTO toDto(Location location) {
        if (location == null) return null;

        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLatitude(location.getLatitude());
        locationDTO.setLongitude(location.getLongitude());

        return locationDTO;
    }

    @Override
    public Location toEntity(LocationDTO locationDTO) {
        if (locationDTO == null) return null;

        Location location = new Location();
        location.setLatitude(locationDTO.getLatitude());
        location.setLongitude(locationDTO.getLongitude());

        return location;
    }

    @Override
    public List<LocationDTO> toDtoList(List<Location> locations) {
        if (locations == null) return null;

        List<LocationDTO> list = new ArrayList<>();
        for (Location location : locations) {
            list.add( toDto(location) );
        }
        return list;
    }

    @Override
    public List<Location> toEntityList(List<LocationDTO> locationDTOs) {
        if (locationDTOs == null) return null;

        List<Location> list = new ArrayList<>();
        for (LocationDTO locationDTO : locationDTOs) {
            list.add( toEntity(locationDTO) );
        }
        return list;
    }
}
