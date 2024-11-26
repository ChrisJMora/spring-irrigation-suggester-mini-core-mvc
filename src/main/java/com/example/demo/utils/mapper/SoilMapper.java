package com.example.demo.utils.mapper;

import com.example.demo.dto.SoilDTO;
import com.example.demo.model.agriculture.Soil;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SoilMapper {
    SoilDTO toDTO(Soil soil);
    Soil toEntity(SoilDTO soilDTO);
    List<SoilDTO> toDtoList(List<Soil> soils);
    List<Soil> toEntityList(List<SoilDTO> soilDTOs);
}
