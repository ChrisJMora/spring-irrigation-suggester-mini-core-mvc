package com.example.demo.utils.mapper;

import com.example.demo.dto.SprinklerDTO;
import com.example.demo.model.agriculture.Sprinkler;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-12T14:29:49-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
public class SprinklerMapperImpl implements SprinklerMapper {

    @Override
    public SprinklerDTO toDTO(Sprinkler sprinklere) {
        if ( sprinklere == null ) {
            return null;
        }

        SprinklerDTO sprinklerDTO = new SprinklerDTO();

        return sprinklerDTO;
    }

    @Override
    public Sprinkler toEntity(SprinklerDTO sprinklerDTO) {
        if ( sprinklerDTO == null ) {
            return null;
        }

        Sprinkler sprinkler = new Sprinkler();

        return sprinkler;
    }

    @Override
    public List<SprinklerDTO> toDtoList(List<Sprinkler> sprinklers) {
        if ( sprinklers == null ) {
            return null;
        }

        List<SprinklerDTO> list = new ArrayList<SprinklerDTO>( sprinklers.size() );
        for ( Sprinkler sprinkler : sprinklers ) {
            list.add( toDTO( sprinkler ) );
        }

        return list;
    }

    @Override
    public List<Sprinkler> toEntityList(List<SprinklerDTO> sprinklerDTOs) {
        if ( sprinklerDTOs == null ) {
            return null;
        }

        List<Sprinkler> list = new ArrayList<Sprinkler>( sprinklerDTOs.size() );
        for ( SprinklerDTO sprinklerDTO : sprinklerDTOs ) {
            list.add( toEntity( sprinklerDTO ) );
        }

        return list;
    }
}
