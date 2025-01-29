package com.example.demo.application.ports.mappers;

import com.example.demo.application.dto.CropDTO;
import com.example.demo.domain.models.Crop;

import java.util.List;

public interface CropMapper {
    CropDTO toDTO(Crop crop);
    Crop toEntity(CropDTO cropDTO);
    List<CropDTO> toDtoList(List<Crop> crops);
    List<Crop> toEntityList(List<CropDTO> cropResponse);
}
