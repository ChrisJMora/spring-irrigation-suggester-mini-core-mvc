package com.example.demo.utils.mapper;

import com.example.demo.dto.LocationDTO;
import com.example.demo.model.agriculture.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    LocationDTO toDto(Location location);
    Location toEntity(LocationDTO locationDTO);
    List<LocationDTO> toDtoList(List<Location> locations);
    List<Location> toEntityList(List<LocationDTO> locationDTOs);
}
