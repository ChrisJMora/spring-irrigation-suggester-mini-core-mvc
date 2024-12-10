package com.example.demo.utils.mapper;

import com.example.demo.dto.CropDTO;
import com.example.demo.model.agriculture.Crop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

public interface CropMapper {
    CropDTO toDTO(Crop crop);
    Crop toEntity(CropDTO cropDTO);
    List<CropDTO> toDtoList(List<Crop> crops);
    List<Crop> toEntityList(List<CropDTO> cropDTOs);
}
