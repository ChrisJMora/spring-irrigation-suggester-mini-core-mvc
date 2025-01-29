package com.example.demo.application.ports.mappers;

import com.example.demo.application.dto.LocationDTO;
import com.example.demo.domain.models.Location;

import java.util.List;

public interface LocationMapper {
    LocationDTO toDto(Location location);
    Location toEntity(LocationDTO locationDTO);
    List<LocationDTO> toDtoList(List<Location> locations);
    List<Location> toEntityList(List<LocationDTO> locationDTOs);
}
