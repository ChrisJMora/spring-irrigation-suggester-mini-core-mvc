package com.example.demo.utils.mapper;

import com.example.demo.dto.SprinklerDTO;
import com.example.demo.model.agriculture.Sprinkler;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SprinklerMapper {
    SprinklerDTO toDTO(Sprinkler sprinkler);
    Sprinkler toEntity(SprinklerDTO sprinklerDTO);
    List<SprinklerDTO> toDtoList(List<Sprinkler> sprinklers);
    List<Sprinkler> toEntityList(List<SprinklerDTO> sprinklerDTOs);
}
