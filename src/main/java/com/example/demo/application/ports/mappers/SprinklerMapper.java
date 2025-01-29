package com.example.demo.application.ports.mappers;

import com.example.demo.application.dto.SprinklerDTO;
import com.example.demo.domain.models.Sprinkler;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SprinklerMapper {
    SprinklerDTO toDTO(Sprinkler sprinklere);
    Sprinkler toEntity(SprinklerDTO sprinklerDTO);
    List<SprinklerDTO> toDtoList(List<Sprinkler> sprinklers);
    List<Sprinkler> toEntityList(List<SprinklerDTO> sprinklerDTOs);
}
