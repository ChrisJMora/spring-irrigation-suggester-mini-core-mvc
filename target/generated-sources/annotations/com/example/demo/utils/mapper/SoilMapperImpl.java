package com.example.demo.utils.mapper;

import com.example.demo.dto.SoilDTO;
import com.example.demo.model.agriculture.Soil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-12T14:38:42-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
public class SoilMapperImpl implements SoilMapper {

    @Override
    public SoilDTO toDTO(Soil soile) {
        if ( soile == null ) {
            return null;
        }

        SoilDTO soilDTO = new SoilDTO();

        return soilDTO;
    }

    @Override
    public Soil toEntity(SoilDTO soilDTO) {
        if ( soilDTO == null ) {
            return null;
        }

        Soil soil = new Soil();

        return soil;
    }

    @Override
    public List<SoilDTO> toDtoList(List<Soil> soils) {
        if ( soils == null ) {
            return null;
        }

        List<SoilDTO> list = new ArrayList<SoilDTO>( soils.size() );
        for ( Soil soil : soils ) {
            list.add( toDTO( soil ) );
        }

        return list;
    }

    @Override
    public List<Soil> toEntityList(List<SoilDTO> soilDTOs) {
        if ( soilDTOs == null ) {
            return null;
        }

        List<Soil> list = new ArrayList<Soil>( soilDTOs.size() );
        for ( SoilDTO soilDTO : soilDTOs ) {
            list.add( toEntity( soilDTO ) );
        }

        return list;
    }
}
