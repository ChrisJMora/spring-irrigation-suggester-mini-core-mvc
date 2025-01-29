package com.example.demo.application.ports.mappers;

import com.example.demo.application.dto.SoilDTO;
import com.example.demo.domain.models.Soil;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SoilMapper {
    SoilDTO toDTO(Soil soile);
    Soil toEntity(SoilDTO soilDTO);
    List<SoilDTO> toDtoList(List<Soil> soils);
    List<Soil> toEntityList(List<SoilDTO> soilDTOs);
}
